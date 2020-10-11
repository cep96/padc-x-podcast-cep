package com.cep.cep_podcast.views.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.cep.cep_podcast.data.vos.DataVO
import com.cep.shared.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_up_next.view.*

class PodcastViewHolder(
    itemView: View,
    private val delegate: Delegate
) : BaseViewHolder<DataVO>(itemView) {

    override fun bindData(data: DataVO) {

        Glide.with(itemView.context)
            .load(data.image)
            .into(itemView.ivPodcastUpNext)

        itemView.tvUpNextPodcastTitle.text = data.title
        itemView.tvUpNextPodcastLeftTime.text = data.audio_length_sec.toString()

        itemView.setOnClickListener {
            delegate.onTapUpNextPodcastItem(data.id)
        }

        itemView.ivDownload.setOnClickListener {
            delegate.onTapBtnDownload(data.audio!!, data.id!!)
        }
    }

    interface Delegate {
        fun onTapUpNextPodcastItem(podcastId: String)
        fun onTapBtnDownload(audio: String, audioId: String)
    }
}