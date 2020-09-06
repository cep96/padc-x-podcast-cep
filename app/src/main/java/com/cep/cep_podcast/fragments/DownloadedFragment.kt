package com.cep.cep_podcast.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.cep.cep_podcast.R
import com.cep.cep_podcast.activities.PodcastDetailsActivity
import com.cep.cep_podcast.adapters.DownloadedRecyclerAdapter
import com.cep.cep_podcast.data.dummydata.DummyPodcast
import com.cep.cep_podcast.data.vos.DownloadedPodcastVO
import com.cep.cep_podcast.mvp.presenters.DownloadedPresenter
import com.cep.cep_podcast.mvp.presenters.impls.DownloadedPresenterImpl
import com.cep.cep_podcast.mvp.views.DownloadView
import com.cep.cep_podcast.views.viewholders.DownloadedViewHolder
import com.cep.cep_podcast.views.viewpods.EmptyViewPod
import kotlinx.android.synthetic.main.fragment_downloaded.*

class DownloadedFragment : Fragment(), DownloadView, DownloadedViewHolder.Delegate {

    private lateinit var mPresenter: DownloadedPresenter

    private lateinit var mViewPodEmpty: EmptyViewPod

    private lateinit var mDownloadAdapter: DownloadedRecyclerAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_downloaded, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()
        setUpViewPod()
        setUpRecycler()

        mPresenter.onUIReadyForDownloadedPodcast(this)

    }

    private fun setUpViewPod() {
        mViewPodEmpty = vpEmpty as EmptyViewPod
    }

    private fun setUpRecycler() {
        mDownloadAdapter = DownloadedRecyclerAdapter(this)

        rvDownload.layoutManager = LinearLayoutManager(this.requireContext())
        rvDownload.adapter = mDownloadAdapter

        rvDownload.setEmptyView(vpEmpty)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProviders.of(this).get(DownloadedPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    override fun displayDownloadedPodcast(podcast: List<DownloadedPodcastVO>) {
        mDownloadAdapter.setNewData(podcast.toMutableList())
    }

    companion object{

        fun newInstance(): Fragment{
            val fragment = DownloadedFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }

    }

    override fun onTapDownloadedPodcastItem(podastId: String) {
        startActivity(PodcastDetailsActivity.getIntent(this.requireContext(), podastId, "download"))
    }
}