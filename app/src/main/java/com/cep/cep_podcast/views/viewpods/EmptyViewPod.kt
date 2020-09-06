package com.cep.cep_podcast.views.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.RelativeLayout
//import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_pod_empty.view.*

class EmptyViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var mDelegate: Delegate ?= null

    override fun onFinishInflate() {
        super.onFinishInflate()

    }

    fun setDelegate(delegate: Delegate){
        mDelegate = delegate
    }

    private fun setUpListener(){
//        btnTryAgain.setOnClickListener {
//            mDelegate?.onTapTryAgain()
//        }
    }

    interface Delegate{
        fun onTapTryAgain()
    }

}