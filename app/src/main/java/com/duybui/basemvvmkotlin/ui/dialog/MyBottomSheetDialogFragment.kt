package com.duybui.basemvvmkotlin.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.duybui.basemvvmkotlin.utils.TAG
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MyBottomSheetDialogFragment : BottomSheetDialogFragment() {
    var layoutId: Int? = null

    lateinit var callback: (View, DialogFragment) -> Unit


    companion object {
        val TAG = MyBottomSheetDialogFragment::TAG.toString()

        fun newInstance(layoutId: Int?): MyBottomSheetDialogFragment {
            val frag = MyBottomSheetDialogFragment()
            frag.arguments = Bundle().apply {
                putInt("layoutId", layoutId!!)
            }
            return frag
        }
    }

    override fun onStart() {
        super.onStart()
        val deviceWith = context!!.resources.displayMetrics.widthPixels
        dialog?.window?.setLayout(deviceWith, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    fun setCustomView(customView: (View, DialogFragment) -> Unit) {
        callback = customView
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layoutId = arguments?.getInt("layoutId")
        if (layoutId == null) {
            throw Exception("LayoutId can't be null")
        }
        return inflater.inflate(layoutId!!, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callback.invoke(view, this)
    }
}