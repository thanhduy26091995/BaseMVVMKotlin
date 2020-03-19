package com.duybui.basemvvmkotlin.ui.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import com.duybui.basemvvmkotlin.MyApplication
import com.duybui.basemvvmkotlin.di.application.ApplicationComponent
import com.duybui.basemvvmkotlin.di.presentation.PresentationComponent
import com.duybui.basemvvmkotlin.di.presentation.PresentationModule
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

    protected fun handleServerError(title: String?, body: String?, code: Int) {
        ServerErrorDialogFragment.newInstance(title, body, code)
            .show(supportFragmentManager, "Error")
    }
}
