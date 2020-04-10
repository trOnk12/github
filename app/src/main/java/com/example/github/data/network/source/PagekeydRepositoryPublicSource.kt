package com.example.github.data.network.source

import androidx.paging.PageKeyedDataSource
import com.example.github.data.network.mapper.public_repository.NetworkGithubRepositoryResponseMapper
import com.example.github.data.network.service.GithubRepositoryService
import com.example.github.domain.model.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PagekeydRepositoryPublicSource(
    private val coroutineScope: CoroutineScope,
    private val githubRepositoryService: GithubRepositoryService,
    private val networkGithubRepositoryResponseMapper: NetworkGithubRepositoryResponseMapper
) : PageKeyedDataSource<String, Repository>() {

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, Repository>
    ) {
        coroutineScope.launch {
            githubRepositoryService.getPublic().let { networkGitHubRepositoryResponse ->
                callback.onResult(
                    networkGithubRepositoryResponseMapper.map(networkGitHubRepositoryResponse),
                    null,
                    networkGitHubRepositoryResponse.pageLinks.nextLink
                )
            }
        }
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Repository>) {
        coroutineScope.launch {
            githubRepositoryService.getByLink(params.key).let { networkGitHubRepositoryResponse ->
                callback.onResult(
                    networkGithubRepositoryResponseMapper.map(networkGitHubRepositoryResponse),
                    networkGitHubRepositoryResponse.pageLinks.nextLink
                )
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<String, Repository>
    ) {

    }

}