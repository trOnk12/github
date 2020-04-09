package com.example.github.feature.repositorylist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import com.example.github.GithubApp
import com.example.github.R
import com.example.github.core.extension.observe
import com.example.github.domain.model.Repository
import com.example.github.feature.repositorylist.adapter.GithubRepositoryAdapter
import kotlinx.android.synthetic.main.layout_search.*
import kotlinx.android.synthetic.main.list_fragment.*
import javax.inject.Inject

class GithubListFragment : Fragment(R.layout.list_fragment) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var githubRepositoryAdapter: GithubRepositoryAdapter

    private val githubRepositoryListViewModel: GithubRepositoryListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[GithubRepositoryListViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        initializeDaggerDependency()
        super.onAttach(context)
    }

    private fun initializeDaggerDependency() {
        DaggerGithubListFragmentComponent
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
        githubRepositoryAdapter = GithubRepositoryAdapter(::onItemClick).also {
            rv_items.adapter = it
        }

        et_search.addTextChangedListener(object : OnSearchTermChangedListener {
            override fun onSearchTermChanged(repositoryName: CharSequence?) {
                repositoryName?.let {
                    observe(githubRepositoryListViewModel.searchRepositories(repositoryName.toString())) {
                        githubRepositoryAdapter.submitList(it)
                    }
                }
            }
        })

        test_button.setOnClickListener { githubRepositoryListViewModel.test() }

    }

    private fun onItemClick(listItem: Repository, position: Int, imageView: ImageView) {

    }

//    private fun onFilterResult(list: List<Repository>) {
//        if (list.isEmpty()) {
//            empty_view.visibility = View.VISIBLE
//            rv_items.visibility = View.GONE
//        } else {
//            empty_view.visibility = View.GONE
//            rv_items.visibility = View.VISIBLE
//            adapter.submitList(list)
//        }
//    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observe(githubRepositoryListViewModel.publicRepositories, ::onRepositoriesChanged)
    }

    private fun onRepositoriesChanged(pagedList: PagedList<Repository>) {
        githubRepositoryAdapter.submitList(pagedList)
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


