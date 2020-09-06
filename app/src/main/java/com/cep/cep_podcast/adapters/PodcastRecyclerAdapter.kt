package com.cep.cep_podcast.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cep.cep_podcast.R
import com.cep.cep_podcast.data.dummydata.DummyPodcast
import com.cep.cep_podcast.data.vos.ItemVO
import com.cep.cep_podcast.views.viewholders.PodcastViewHolder

class PodcastRecyclerAdapter(
    private val delegate: PodcastViewHolder.Delegate
): BaseRecyclerAdapter<PodcastViewHolder, ItemVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PodcastViewHolder {
        return PodcastViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_up_next, parent, false),
            delegate
        )
    }
}