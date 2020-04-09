package com.example.github.data.network.source

import androidx.paging.PageKeyedDataSource
import com.example.github.data.network.mapper.NetworkGithubRepositoryResponseMapper
import com.example.github.data.network.service.GithubRepositoryService
import com.example.github.domain.model.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PageKeyedRepositorySearchSource(
    private val repositoryName:String,
    private val coroutineScope: CoroutineScope,
    private val networkGithubRepositoryResponseMapper: NetworkGithubRepositoryResponseMapper,
    private val githubRepositoryService: GithubRepositoryService
) : PageKeyedDataSource<String, Repository>() {

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, Repository>
    ) {
        coroutineScope.launch {
            githubRepositoryService.get(repositoryName).let { networkGitHubRepositoryResponse ->
                callback.onResult(
                    networkGithubRepositoryResponseMapper.map(networkGitHubRepositoryResponse),
                    null,
                    networkGitHubRepositoryResponse.paginationInfo.nextLink
                )
            }
        }
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Repository>) {
        coroutineScope.launch {
            githubRepositoryService.getByLink(params.key).let { networkGitHubRepositoryResponse ->
                callback.onResult(
                    networkGithubRepositoryResponseMapper.map(networkGitHubRepositoryResponse),
                    networkGitHubRepositoryResponse.paginationInfo.nextLink
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