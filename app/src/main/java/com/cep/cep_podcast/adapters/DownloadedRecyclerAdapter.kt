package com.cep.cep_podcast.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cep.cep_podcast.R
import com.cep.cep_podcast.data.dummydata.DummyPodcast
import com.cep.cep_podcast.data.vos.DownloadedPodcastVO
import com.cep.cep_podcast.views.viewholders.DownloadedViewHolder

class DownloadedRecyclerAdapter(
    private val delegate: DownloadedViewHolder.Delegate
): BaseRecyclerAdapter<DownloadedViewHolder, DownloadedPodcastVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DownloadedViewHolder {
        return DownloadedViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_downloaded, parent, false),
            delegate
        )
    }
}