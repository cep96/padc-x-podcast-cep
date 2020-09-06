package com.cep.cep_podcast.views.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.cep.cep_podcast.data.dummydata.DummyPodcast
import com.cep.cep_podcast.data.vos.GenreVO
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryViewHolder(itemView: View) : BaseViewHolder<GenreVO>(itemView) {

    override fun bindData(data: GenreVO) {

        itemView.tvCategory.text = data.name

        Glide.with(itemView.context)
            .load(data.image)
            .into(itemView.ivCategoryImage)

        itemView.setOnClickListener {

        }
    }
}