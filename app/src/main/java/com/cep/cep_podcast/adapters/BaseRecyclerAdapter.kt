package com.cep.cep_podcast.adapters

import androidx.recyclerview.widget.RecyclerView
import com.cep.cep_podcast.views.viewholders.BaseViewHolder

abstract class BaseRecyclerAdapter<T: BaseViewHolder<W>,W>:
    RecyclerView.Adapter<T>() {

    var mData: MutableList<W> = arrayListOf()

    override fun getItemCount(): Int {
        return mData.count()
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.bindData(mData[position])
    }

    fun setNewData(data: MutableList<W>){
        mData = data
        notifyDataSetChanged()
    }

}