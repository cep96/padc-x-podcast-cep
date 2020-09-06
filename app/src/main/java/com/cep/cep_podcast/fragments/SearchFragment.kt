package com.cep.cep_podcast.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.cep.cep_podcast.R
import com.cep.cep_podcast.adapters.CategoryRecyclerAdapter
import com.cep.cep_podcast.data.dummydata.DummyPodcast
import com.cep.cep_podcast.data.vos.GenreVO
import com.cep.cep_podcast.mvp.presenters.SearchPresenter
import com.cep.cep_podcast.mvp.presenters.impls.SearchPresenterImpl
import com.cep.cep_podcast.mvp.views.SearchView
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment(), SearchView {

    private lateinit var mPresenter: SearchPresenter

    private lateinit var mCategoryAdapter: CategoryRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()
        setUpRecycler()

        mPresenter.onUIReadyForCategories(this)

    }

    private fun setUpRecycler() {
        mCategoryAdapter = CategoryRecyclerAdapter()

        rvCategory.layoutManager = LinearLayoutManager(
            this.requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvCategory.adapter = mCategoryAdapter
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProviders.of(this).get(SearchPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SearchFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun displayCategories(categories: List<GenreVO>) {
        mCategoryAdapter.setNewData(categories.toMutableList())
    }
}