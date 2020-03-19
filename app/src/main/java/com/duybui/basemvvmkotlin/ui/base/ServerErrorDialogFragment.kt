package com.duybui.basemvvmkotlin.ui.base

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.duybui.basemvvmkotlin.R
import com.duybui.basemvvmkotlin.utils.AppConstants

class ServerErrorDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertDialogBuilder = AlertDialog.Builder(activity)

        var title: String? = getString(R.string.server_error_dialog_title)
        var body: String? = getString(R.string.server_error_dialog_message)
        var code = -1

        //get title and body
        arguments?.let {
            title = it.getString(AppConstants.TITLE)
            body = it.getString(AppConstants.BODY)
            code = it.getInt(AppConstants.CODE)
        }

        alertDialogBuilder.setTitle(title)

        alertDialogBuilder.setMessage(body)

        alertDialogBuilder.setPositiveButton(
            R.string.server_error_dialog_button_caption
        ) { _, _ ->
            if (code == 404) {
                Toast.makeText(context, "404", Toast.LENGTH_SHORT).show()
            }
            dismiss()
        }
        return alertDialogBuilder.create()
    }

    companion object {

        fun newInstance(title: String?, body: String?, code: Int): ServerErrorDialogFragment {
            val bundle = Bundle()
            bundle.putString(AppConstants.TITLE, title)
            bundle.putString(AppConstants.BODY, body)
            bundle.putInt(AppConstants.CODE, code)
            val serverErrorDialogFragment = ServerErrorDialogFragment()
            serverErrorDialogFragment.arguments = bundle
            return serverErrorDialogFragment
        }
    }
}
