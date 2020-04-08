package com.example.github.data.network.service

import android.util.Log
import com.example.github.data.network.api.GithubRepositoryApi
import com.example.github.data.network.model.NetworkGitHubRepositoryResponse
import com.example.github.data.network.tools.PageLinks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.resume


const val NEXT_PAGE_INDEX = 8

class GithubRepositoryService
@Inject constructor(
    private val githubRepositoryApi: GithubRepositoryApi,
    private val pageLinksMapper: PageLinksMapper
) {

    suspend fun get(): NetworkGitHubRepositoryResponse =
        withContext(Dispatchers.IO) {
            suspendCancellableCoroutine<NetworkGitHubRepositoryResponse> { continuation ->
                val response = githubRepositoryApi.get().execute()
                if (response.isSuccessful) {
                    response.headers().value(NEXT_PAGE_INDEX)
                        .let { link ->
                            val nextPageInfo = pageLinksMapper.map(PageLinks(link))
                            response.body()?.let { networkGithubRepositories ->
                                continuation.resume(
                                    NetworkGitHubRepositoryResponse(
                                        nextPageInfo = nextPageInfo,
                                        networkGitHubRepositories = networkGithubRepositories
                                    )
                                )
                            }
                        }

                } else {
                    response.errorBody()?.let { responseBody ->
                        throw Exception(responseBody.string())
                    }

                }
            }
        }

}
