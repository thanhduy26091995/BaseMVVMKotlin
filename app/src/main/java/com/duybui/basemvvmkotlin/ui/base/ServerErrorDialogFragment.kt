package com.duybui.basemvvmkotlin.ui.base

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.duybui.basemvvmkotlin.R
import com.duybui.basemvvmkotlin.utils.AppConstants

class ServerErrorDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertDialogBuilder = AlertDialog.Builder(activity)

        var title: String? = getString(R.string.server_error_dialog_title)
        var body: String? = getString(R.string.server_error_dialog_message)

        //get title and body
        if (arguments != null) {
            title = arguments!!.getString(AppConstants.TITLE)
            body = arguments!!.getString(AppConstants.BODY)
        }
        alertDialogBuilder.setTitle(title)

        alertDialogBuilder.setMessage(body)

        alertDialogBuilder.setPositiveButton(
            R.string.server_error_dialog_button_caption,
            { dialog, which -> dismiss() }
        )
        return alertDialogBuilder.create()
    }

    companion object {

        fun newInstance(title: String?, body: String?): ServerErrorDialogFragment {
            val bundle = Bundle()
            bundle.putString(AppConstants.TITLE, title)
            bundle.putString(AppConstants.BODY, body)
            val serverErrorDialogFragment = ServerErrorDialogFragment()
            serverErrorDialogFragment.arguments = bundle
            return serverErrorDialogFragment
        }
    }
}
