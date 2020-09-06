package com.cep.cep_podcast.views.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.cep.cep_podcast.data.vos.DataVO
import com.cep.cep_podcast.data.vos.ItemVO
import kotlinx.android.synthetic.main.item_up_next.view.*

class PodcastViewHolder(
    itemView: View,
    private val delegate: Delegate
) : BaseViewHolder<ItemVO>(itemView) {

    override fun bindData(data: ItemVO) {

        Glide.with(itemView.context)
            .load(data.data.image)
            .into(itemView.ivPodcastUpNext)

        itemView.tvUpNextPodcastTitle.text = data.data.title
        itemView.tvUpNextPodcastLeftTime.text = data.data.audio_length_sec.toString()

        itemView.setOnClickListener {
            delegate.onTapUpNextPodcastItem(data.data.id)
        }

        itemView.ivDownload.setOnClickListener {
            delegate.onTapBtnDownload(data.data.audio, data.data.id)
        }
    }

    interface Delegate {
        fun onTapUpNextPodcastItem(podcastId: String)
        fun onTapBtnDownload(audio: String, audioId: String)
    }
}