package com.example.github.feature.repositorylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.github.data.network.mapper.public_repository.NetworkGithubRepositoryResponseMapper
import com.example.github.data.network.service.GithubRepositoryService
import com.example.github.data.network.source.PageKeyedRepositorySearchSource
import com.example.github.data.network.source.PagekeydRepositoryPublicSource
import com.example.github.domain.model.Repository
import javax.inject.Inject

class GithubRepositoryListViewModel
@Inject constructor(
    private val networkGithubRepositoryResponseMapper: NetworkGithubRepositoryResponseMapper,
    private val githubRepositoryService: GithubRepositoryService
) : ViewModel() {

    private val config = PagedList.Config.Builder()
        .setPageSize(30)
        .setEnablePlaceholders(false)
        .build()

    var publicRepositories: LiveData<PagedList<Repository>> =
        initializePublicRepositoriesPagedList(config).build()

    private fun initializePublicRepositoriesPagedList(config: PagedList.Config): LivePagedListBuilder<String, Repository> {
        val dataSourceFactory = object : DataSource.Factory<String, Repository>() {
            override fun create(): DataSource<String, Repository> {
              return PagekeydRepositoryPublicSource(
                    viewModelScope,
                    networkGithubRepositoryResponseMapper,
                    githubRepositoryService
                )
            }

        }

        return LivePagedListBuilder<String, Repository>(dataSourceFactory, config)
    }

    fun searchRepositories(repositoryName: String): LiveData<PagedList<Repository>> {
        return initializeSearchRepositoriesPagedList(config, repositoryName).build()
    }

    private fun initializeSearchRepositoriesPagedList(
        config: PagedList.Config,
        repositoryName: String
    ): LivePagedListBuilder<String, Repository> {
        val pageKeyedRepositorySearchSource = PageKeyedRepositorySearchSource(
            repositoryName,
            viewModelScope,
            networkGithubRepositoryResponseMapper,
            githubRepositoryService
        )

        val dataSourceFactory = object : DataSource.Factory<String, Repository>() {
            override fun create(): DataSource<String, Repository> {
                return pageKeyedRepositorySearchSource
            }
        }

        return LivePagedListBuilder<String, Repository>(dataSourceFactory, config)
    }

    fun test() {

    }

}

sealed class ListFragmentViewEvent {
    data class ShowErrorMessage(val message: String?) : ListFragmentViewEvent()
}

