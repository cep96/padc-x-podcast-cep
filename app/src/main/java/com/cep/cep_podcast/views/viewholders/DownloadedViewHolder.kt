package com.cep.cep_podcast.views.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.cep.cep_podcast.data.dummydata.DummyPodcast
import com.cep.cep_podcast.data.vos.DownloadedPodcastVO
import com.cep.shared.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_downloaded.view.*

class DownloadedViewHolder(itemView: View, private val delegate: Delegate) : BaseViewHolder<DownloadedPodcastVO>(itemView) {

    override fun bindData(data: DownloadedPodcastVO) {

        Glide.with(itemView.context)
            .load(data.audioDetails.image)
            .into(itemView.ivDownloadPodcast)

        itemView.tvDownloadedPodcastTitle.text = data.audioDetails.title
        itemView.tvDownloadedPodcastDesc.text = data.audioDetails.description

        itemView.setOnClickListener {
            delegate.onTapDownloadedPodcastItem(data.audioId)
        }
    }

    interface Delegate{
        fun onTapDownloadedPodcastItem(podastId: String)
    }
}