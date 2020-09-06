package com.cep.cep_podcast.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.cep.cep_podcast.R
import com.cep.cep_podcast.data.vos.DownloadedPodcastVO
import com.cep.cep_podcast.mvp.presenters.PodcastDetailsPresenter
import com.cep.cep_podcast.mvp.presenters.impls.PodcastDetailsPresenterImpl
import com.cep.cep_podcast.mvp.views.PodcastDetailsView
import com.cep.cep_podcast.network.responses.GetDetailsEpisodeResponse
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_podcast_details.*
import kotlinx.android.synthetic.main.activity_podcast_details.playerControlView
import kotlinx.android.synthetic.main.activity_podcast_details.tvPodcastDescription
import kotlinx.android.synthetic.main.content_collapsing.*

class PodcastDetailsActivity : AppCompatActivity(), PodcastDetailsView {

    private lateinit var mPresenter: PodcastDetailsPresenter
    private var podcastId = ""
    private var podcastType = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_podcast_details)

        podcastId = intent.getStringExtra(PODCAST_ID_EXTRA)
        podcastType = intent.getStringExtra(PODCAST_TYPE)

        setUpPresenter()

        mPresenter.onUIReadyForPodcast(this, podcastId, podcastType)


    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProviders.of(this).get(PodcastDetailsPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    companion object {

        const val PODCAST_ID_EXTRA = "PODCAST_ID_EXTRA"
        const val PODCAST_TYPE = "PODCAST_TYPE"

        fun getIntent(context: Context, podcastId: String, type: String): Intent {
            val intent = Intent(context, PodcastDetailsActivity::class.java)
            intent.putExtra(PODCAST_ID_EXTRA, podcastId)
            intent.putExtra(PODCAST_TYPE, type)
            return intent
        }
    }

    override fun displayPodcastDetails(podcast: GetDetailsEpisodeResponse) {

        Glide.with(this)
            .load(podcast.image)
            .into(ivCategoryImage)

        tvTitle.text = podcast.podcast.title

        tvPodcastDescription.text = Html.fromHtml(podcast.podcast.description)

        val url = podcast.audio

        playerControlView.player = mPresenter.getPlayer().getPlayerImpl(this)
        Log.d("Podcast Url", "==> $url")
        mPresenter.play(url)

    }

    override fun displayDownloadedPodcastDetails(podcast: DownloadedPodcastVO) {
        Glide.with(this)
            .load(podcast.audioDetails.image)
            .into(ivCategoryImage)

        tvTitle.text = podcast.audioDetails.title

        tvPodcastDescription.text = Html.fromHtml(podcast.audioDetails.description)

        val url = podcast.audioUrl

        playerControlView.player = mPresenter.getPlayer().getPlayerImpl(this)
        Log.d("Podcast Url", "==> $url")
        mPresenter.play(url)
    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23) {
            mPresenter.getPlayer().getPlayerImpl(this)
        }
    }

    override fun onResume() {
        super.onResume()

        hideSystemUi()
        if (Util.SDK_INT <= 23) {
            mPresenter.getPlayer().getPlayerImpl(this)
        }
    }

    override fun onStop() {
        super.onStop()

        if (Util.SDK_INT <= 23) {
            mPresenter.releasePlayer()
        }
    }

    override fun onPause() {
        super.onPause()

        if (Util.SDK_INT <= 23) {
            mPresenter.releasePlayer()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.releasePlayer()
    }

    @SuppressLint("InlinedApi")
    private fun hideSystemUi() {
        playerControlView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }
}