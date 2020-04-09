package com.example.github.data.network.source

import androidx.paging.PageKeyedDataSource
import com.example.github.data.network.mapper.RepositoryMapper
import com.example.github.data.network.service.GithubRepositoryService
import com.example.github.domain.model.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class PageKeyedRepositorySource @Inject constructor(
    private val githubRepositoryService: GithubRepositoryService,
    private val repositoryMapper: RepositoryMapper
) : PageKeyedDataSource<String, Repository>() {

    var coroutineScope: CoroutineScope? = null
    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, Repository>
    ) {
        if (coroutineScope == null) throw Exception("No coroutinescope defined")

        coroutineScope?.launch {
            githubRepositoryService.get().let { response ->
                callback.onResult(
                    response.networkGitHubRepositories.map { repository ->
                        repositoryMapper.map(
                            repository
                        )
                    },
                    response.headerData.previousLink,
                    response.headerData.nextLink
                )
            }
        }
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Repository>) {
        if (coroutineScope == null) throw Exception("No coroutinescope defined")

        coroutineScope?.launch {
            githubRepositoryService.get().let { response ->
                callback.onResult(
                    response.networkGitHubRepositories.map { repository ->
                        repositoryMapper.map(
                            repository
                        )
                    },
                    response.headerData.previousLink,
                    response.headerData.nextLink
                )
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<String, Repository>
    ) {
        if (coroutineScope == null) throw Exception("No coroutinescope defined")
    }


}