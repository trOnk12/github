package com.example.github.feature.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.github.data.network.mapper.NetworkGithubRepositoryResponseMapper
import com.example.github.data.network.service.GithubRepositoryService
import com.example.github.data.network.source.PageKeyedRepositorySource
import com.example.github.domain.model.Repository
import javax.inject.Inject

class ListFragmentViewModel
@Inject constructor(
    private val networkGithubRepositoryResponseMapper: NetworkGithubRepositoryResponseMapper,
    private val githubRepositoryService: GithubRepositoryService
) : ViewModel() {

    var repositories: LiveData<PagedList<Repository>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setEnablePlaceholders(false)
            .build()
        repositories = initializedPagedListBuilder(config).build()
    }

    private fun initializedPagedListBuilder(config: PagedList.Config): LivePagedListBuilder<String, Repository> {
        val dataSourceFactory = object : DataSource.Factory<String, Repository>() {
            override fun create(): DataSource<String, Repository> {
                return PageKeyedRepositorySource(
                    viewModelScope,
                    networkGithubRepositoryResponseMapper,
                    githubRepositoryService
                )
            }

        }

        return LivePagedListBuilder<String, Repository>(dataSourceFactory, config)
    }

}

sealed class ListFragmentViewEvent {
    data class ShowErrorMessage(val message: String?) : ListFragmentViewEvent()
}

