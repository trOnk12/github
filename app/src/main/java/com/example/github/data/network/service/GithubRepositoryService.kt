package com.example.github.data.network.service

import com.example.github.data.network.api.GithubRepositoryApi
import com.example.github.data.network.mapper.PageLinkMapper
import com.example.github.data.network.model.NetworkGitHubRepositoryResponse
import com.example.github.data.network.model.NetworkGithubRepository
import com.example.github.data.network.tools.PageLinks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


const val NEXT_PAGE_INDEX = 8

class GithubRepositoryService
@Inject constructor(
    private val githubRepositoryApi: GithubRepositoryApi,
    private val pageLinkMapper: PageLinkMapper
) {

    suspend fun getPublic(): NetworkGitHubRepositoryResponse =
        withContext(Dispatchers.IO) {
            suspendCoroutine<NetworkGitHubRepositoryResponse> { continuation ->
                githubRepositoryApi.get().execute().let { response ->
                    if (response.isSuccessful) {
                        response.headers().value(NEXT_PAGE_INDEX).let { link ->
                            response.body()?.let { networkGithubRepositories ->
                                continuation.resume(
                                    NetworkGitHubRepositoryResponse(
                                        paginationInfo = pageLinkMapper.map(PageLinks(link)),
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

    suspend fun getByLink(link: String) =
        withContext(Dispatchers.IO) {
            suspendCoroutine<NetworkGitHubRepositoryResponse> { continuation ->
                githubRepositoryApi.fetchLink(link).execute().let { response ->
                    if (response.isSuccessful) {
                        response.headers().value(NEXT_PAGE_INDEX).let { link ->
                            response.body()?.let { networkGithubRepositories ->
                                continuation.resume(
                                    NetworkGitHubRepositoryResponse(
                                        paginationInfo = pageLinkMapper.map(PageLinks(link)),
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



}
