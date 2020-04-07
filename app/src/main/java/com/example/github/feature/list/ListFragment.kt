package com.example.github.feature.list

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.github.GithubApp
import com.example.github.R
import com.example.github.core.extension.observe
import com.example.github.domain.model.Repository
import com.example.github.feature.list.adapter.ListAdapter
import dog.snow.androidrecruittest.feature.list.DaggerListFragmentComponent
import kotlinx.android.synthetic.main.layout_search.*
import kotlinx.android.synthetic.main.list_fragment.*
import javax.inject.Inject

class ListFragment : Fragment(R.layout.list_fragment) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var adapter: ListAdapter

    private val listItemViewModel: ListFragmentViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ListFragmentViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        initializeDaggerDependency()
        super.onAttach(context)
    }

    private fun initializeDaggerDependency() {
        DaggerListFragmentComponent
            .builder()
            .coreComponent(GithubApp.coreComponent(requireContext()))
            .build()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
    }

    private fun initializeView() {
        adapter = ListAdapter(::onItemClick, ::onFilterResult).also {
            rv_items.adapter = it
        }

        et_search.addTextChangedListener(object : OnSearchTermChangedListener {
            override fun onSearchTermChanged(p0: CharSequence?) {
                p0?.let {
                    adapter.filterItems(p0)
                }
            }
        })
    }

    private fun onItemClick(listItem: Repository, position: Int, imageView: ImageView) {

    }

    private fun onFilterResult(list: List<Repository>) {
        if (list.isEmpty()) {
            empty_view.visibility = View.VISIBLE
            rv_items.visibility = View.GONE
        } else {
            empty_view.visibility = View.GONE
            rv_items.visibility = View.VISIBLE
            adapter.submitList(list)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observe(listItemViewModel.listFragmentViewEvent, ::onViewEvent)
        observe(listItemViewModel.listItem, ::onListItemChanged)

        if (savedInstanceState == null)
            listItemViewModel.fetchData()
    }

    private fun onListItemChanged(listItems: List<Repository>) {
        adapter.setData(listItems)

        rv_items.visibility = View.VISIBLE
        empty_view.visibility = View.GONE
    }

    private fun onViewEvent(viewEvent: ListFragmentViewEvent) {
        when (viewEvent) {
            is ListFragmentViewEvent.ShowErrorMessage -> showError(viewEvent.message)
        }
    }

    private fun showError(errorMessage: String?) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
    }

}


