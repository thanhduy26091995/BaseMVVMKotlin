package com.duybui.basemvvmkotlin.ui.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.duybui.basemvvmkotlin.MyApplication
import com.duybui.basemvvmkotlin.R
import com.duybui.basemvvmkotlin.di.application.ApplicationComponent
import com.duybui.basemvvmkotlin.di.presentation.PresentationComponent
import com.duybui.basemvvmkotlin.di.presentation.PresentationModule
import com.duybui.basemvvmkotlin.ui.dialog.Alert.Companion.alert
import com.duybui.basemvvmkotlin.ui.dialog.AlertData
import com.duybui.basemvvmkotlin.ui.dialog.BottomSheetDialogDSLBuilder.Companion.bottomSheetDialog
import com.duybui.basemvvmkotlin.ui.dialog.CustomAlert.Companion.customAlert
import com.duybui.basemvvmkotlin.ui.dialog.DialogDSLBuilder.Companion.dialog
import kotlinx.android.synthetic.main.layout_dialog.view.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper


abstract class BaseActivity : AppCompatActivity() {

    private var mIsInjectorUsed: Boolean = false

    protected val presentationComponent: PresentationComponent
        @UiThread
        get() {
            if (mIsInjectorUsed) {
                throw RuntimeException("there is no need to use injector more than once")
            }
            mIsInjectorUsed = true
            return applicationComponent
                .newPresentationComponent(PresentationModule(this))

        }

    @get:LayoutRes
    abstract val layoutRes: Int

    private val applicationComponent: ApplicationComponent
        get() = (application as MyApplication).applicationComponent!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
    }


    override fun onDestroy() {
        super.onDestroy()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    protected fun handleServerError(title: String?, body: String?, code: Int, context: Context) {
        alert {
            this.title = title
            this.description = body
            this.context = context
            positiveButton = {
                if (code == 404) {
                    Toast.makeText(context, "404", Toast.LENGTH_SHORT).show()
                }
            }
            negativeButton = {

            }
        }
    }

    protected fun customDialogFragment(title: String, body: String, context: Context) {
        dialog {
            layoutInt = R.layout.layout_dialog
            setCustomView = { it: View, dialog: DialogFragment ->
                it.title.text = title
                it.description.text = body

                it.accept.setOnClickListener {
                    Toast.makeText(context, "Accept", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }

                it.reject.setOnClickListener {
                    Toast.makeText(context, "Reject", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
            }
        }
    }

    protected fun customAlertDialog(context: Context, layoutId: Int) {
        val alertData = AlertData(
            "This is a title",
            "This is a description"
        )
        customAlert {
            alertContext = context
            this.layoutId = layoutId
            setCustomView = { it: View, alertDialog: android.app.AlertDialog ->
                it.title.text = alertData.title
                it.description.text = alertData.description

                it.accept.setOnClickListener {

                }

                it.reject.setOnClickListener {

                }
            }
        }
    }

    protected fun showBottomSheet(context: Context, layoutId: Int?) {
        bottomSheetDialog {
            alertContext = context
            this.layoutId = layoutId
            setCustomView = { it: View, dialog: DialogFragment ->

                it.accept.setOnClickListener {
                    dialog.dismiss()
                }

                it.reject.setOnClickListener {
                    dialog.dismiss()
                }
            }
        }
    }
}
