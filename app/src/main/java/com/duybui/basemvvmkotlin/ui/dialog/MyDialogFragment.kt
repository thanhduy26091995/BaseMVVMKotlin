package com.duybui.basemvvmkotlin.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.duybui.basemvvmkotlin.utils.TAG

class MyDialogFragment : DialogFragment() {
    var layoutId: Int? = null
    lateinit var callBack: (View, DialogFragment) -> Unit


    companion object {
        val TAG = MyDialogFragment::TAG.toString()

        fun newInstance(layoutId: Int?): MyDialogFragment {
            val frag = MyDialogFragment()
            frag.arguments = Bundle().apply {
                putInt("layoutId", layoutId!!)
            }
            return frag
        }
    }

    fun setCustomView(customView: (View, DialogFragment) -> Unit){
        callBack = customView
    }
    override fun onStart() {
        super.onStart()
        context?.let {
            val deviceWidth = it.resources.displayMetrics.widthPixels
            dialog?.window?.setLayout(deviceWidth, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layoutId = arguments?.getInt("layoutId")
        layoutId?.let {
            return inflater.inflate(it, container, false)
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callBack.invoke(view, this)
    }


}