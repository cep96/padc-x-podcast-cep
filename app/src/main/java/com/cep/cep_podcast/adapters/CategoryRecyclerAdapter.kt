package com.cep.cep_podcast.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cep.cep_podcast.R
import com.cep.cep_podcast.data.dummydata.DummyPodcast
import com.cep.cep_podcast.data.vos.GenreVO
import com.cep.cep_podcast.views.viewholders.CategoryViewHolder

class CategoryRecyclerAdapter: BaseRecyclerAdapter<CategoryViewHolder, GenreVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_category, parent, false)
        )
    }
}