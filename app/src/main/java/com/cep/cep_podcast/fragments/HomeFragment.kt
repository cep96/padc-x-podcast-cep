package com.cep.cep_podcast.fragments

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.cep.cep_podcast.R
import com.cep.cep_podcast.activities.PodcastDetailsActivity
import com.cep.cep_podcast.adapters.PodcastRecyclerAdapter
import com.cep.cep_podcast.data.vos.DataVO
import com.cep.cep_podcast.data.vos.PodcastDetailsVO
import com.cep.cep_podcast.mvp.presenters.HomePresenter
import com.cep.cep_podcast.mvp.presenters.impls.HomePresenterImpl
import com.cep.cep_podcast.mvp.views.HomeView
import com.cep.cep_podcast.network.responses.GetRandomEpisodeResponse
import com.cep.cep_podcast.utils.MarshMallowPermissionUtils
import com.cep.cep_podcast.views.viewholders.PodcastViewHolder
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.content_collapsing.*
import kotlinx.android.synthetic.main.content_scrolling.*
import java.io.File

class HomeFragment : Fragment(), HomeView, PodcastViewHolder.Delegate {

    private lateinit var mPresenter: HomePresenter

    private lateinit var mUpNextPodcastAdapter: PodcastRecyclerAdapter

    private lateinit var marshMallowPermissionUtils: MarshMallowPermissionUtils

    private var downloadID: Long = 0
    private var downloadAudioId: String = ""
    private lateinit var file: File

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        marshMallowPermissionUtils = MarshMallowPermissionUtils(this.requireActivity())

        setUpPresenter()
        setUpRecycler()

        activity?.registerReceiver(
            onDownloadComplete,
            IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        )

        mPresenter.onUIReadyForPodcasts(this)
        mPresenter.onUIReadyForRandomPodcast(this)

    }

    private fun setUpRecycler() {
        mUpNextPodcastAdapter = PodcastRecyclerAdapter(this)

        rvUpNext.layoutManager = LinearLayoutManager(this.requireContext())
        rvUpNext.adapter = mUpNextPodcastAdapter
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProviders.of(this).get(HomePresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    companion object {
        fun newInstance(): Fragment {
            val fragment = HomeFragment()
            return fragment
        }
    }

    override fun onTapUpNextPodcastItem(podcastId: String) {
        startActivity(PodcastDetailsActivity.getIntent(this.requireContext(), podcastId, "stream"))
    }

    override fun onTapBtnDownload(audio: String, audioId: String) {
        if (!marshMallowPermissionUtils.checkPermissionForReadExternalStorage()
            || !marshMallowPermissionUtils.checkPermissionForWriteExternalStorage()
        ) {

            marshMallowPermissionUtils.requestPermissions()

        }

        beginDownload(audio)
        downloadAudioId = audioId

    }

    override fun displayPodcast(podcasts: List<DataVO>) {
        Log.d("HomeFragment", "==> $podcasts and ${podcasts.size}")

        mUpNextPodcastAdapter.setNewData(podcasts.toMutableList())
    }

    override fun displayRandomPodcast(podcast: PodcastDetailsVO) {

        Glide.with(this.requireContext())
            .load(podcast.image)
            .into(ivPodcastIcon)

        tvPodcastTitle.text = podcast.title
        tvDescription.text = Html.fromHtml(podcast.description)

        val url = podcast.listennotes_url

        playerControlView.player = mPresenter.getPlayer().getPlayerImpl(this.requireContext())
        Log.d("Podcast Url", "==> $url")
        mPresenter.play(url!!)


    }

    override fun showErrorMessage(message: String) {
        Snackbar.make(requireActivity().window.decorView, message, Snackbar.LENGTH_LONG)
    }

    override fun onStart() {
        super.onStart()

        mPresenter.getPlayer().getPlayerImpl(this.requireContext())

    }

    override fun onResume() {
        super.onResume()

        hideSystemUi()

        mPresenter.getPlayer().getPlayerImpl(this.requireContext())

    }

    override fun onStop() {
        super.onStop()

        mPresenter.releasePlayer()

    }

    override fun onPause() {
        super.onPause()

        mPresenter.releasePlayer()

    }

    override fun onDestroy() {
        super.onDestroy()

        activity?.unregisterReceiver(onDownloadComplete)
    }

    private fun beginDownload(audio: String) {
//        val url = "https://www.listennotes.com/e/p/11b34041e804491b9704d11f283c74de/"
        file = File(activity?.getExternalFilesDir(null), "Podcast")

        val request = DownloadManager.Request(Uri.parse(audio))
            .setTitle("Dummy File") // Title of the Download Notification
            .setDescription("Downloading") // Description of the Download Notification
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE) // Visibility of the download Notification
            .setDestinationUri(Uri.fromFile(file)) // Uri of the destination file
            .setAllowedOverMetered(true) // Set if download is allowed on Mobile network
            .setAllowedOverRoaming(true) // Set if download is allowed on roaming network

        val downloadManager =
            activity?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadID =
            downloadManager.enqueue(request) // enqueue puts the download request in the queue.

    }

    private val onDownloadComplete: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            if (downloadID === id) {
                Toast.makeText(context, "Download Completed", Toast.LENGTH_SHORT).show()

                mPresenter.onReadyForDownload(this@HomeFragment, file, downloadAudioId)

            }
        }
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