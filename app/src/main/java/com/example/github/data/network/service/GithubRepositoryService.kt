package com.example.github.data.network.service

import NetworkGithubRepositorySearchResponse
import com.example.github.data.network.api.GithubRepositoryApi
import com.example.github.data.network.mapper.PagesinkMapper
import com.example.github.data.network.model.public_repository.NetworkGitHubRepositoryResponse
import com.example.github.data.network.model.public_repository.NetworkGithubRepository
import com.example.github.data.network.model.search.NetworkGithubSearch
import com.example.github.data.network.tools.PageLinks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


const val PAGE_LINKS_HEADER_INDEX = 8

class GithubRepositoryService
@Inject constructor(
    private val githubRepositoryApi: GithubRepositoryApi,
    private val pageLinksMapper: PagesinkMapper
) {

    suspend fun getPublic(): NetworkGitHubRepositoryResponse =
        withContext(Dispatchers.IO) {
            suspendCoroutine<NetworkGitHubRepositoryResponse> { continuation ->
                githubRepositoryApi.get().execute().let { response ->
                    if (response.isSuccessful) {
                        handlePublicRepositoriesResponse(response, continuation)
                    } else {
                        handleError(response.errorBody())
                    }
                }
            }
        }

    suspend fun getByLink(link: String) =
        withContext(Dispatchers.IO) {
            suspendCoroutine<NetworkGitHubRepositoryResponse> { continuation ->
                githubRepositoryApi.fetchLink(link).execute().let { response ->

                    if (response.isSuccessful) {
                        handlePublicRepositoriesResponse(response, continuation)
                    } else {
                        handleError(response.errorBody())
                    }
                }
            }
        }

    private fun handlePublicRepositoriesResponse(
        response: Response<List<NetworkGithubRepository>>,
        continuation: Continuation<NetworkGitHubRepositoryResponse>
    ) {
        response.headers().value(PAGE_LINKS_HEADER_INDEX).let { pageLinks ->
            response.body()?.let { networkGithubRepositories ->

                val networkGitHubRepositoryResponse =
                    NetworkGitHubRepositoryResponse(
                        pageLinks = pageLinksMapper.map(
                            PageLinks(pageLinks)
                        ),
                        networkGitHubRepositories = networkGithubRepositories
                    )

                continuation.resume(networkGitHubRepositoryResponse)

            }
        }
    }

    suspend fun search(repositoryName: String) =
        withContext(Dispatchers.IO) {
            suspendCoroutine<NetworkGithubSearch> { continuation ->
                githubRepositoryApi.search(repositoryName).execute().let { response ->
                    if (response.isSuccessful) handleSearchRepositoryResponse(
                        response,
                        continuation
                    ) else handleError(response.errorBody())
                }
            }
        }

    private fun handleSearchRepositoryResponse(
        response: Response<NetworkGithubRepositorySearchResponse>,
        continuation: Continuation<NetworkGithubSearch>
    ) {
        response.headers().value(PAGE_LINKS_HEADER_INDEX).let { pageLInks ->
            response.body()?.let { networkGithubRepositorySearchResponse ->

                val networkGithubSearch =
                    NetworkGithubSearch(
                        paginationInfo = pageLinksMapper.map(PageLinks(pageLInks)),
                        networkGitHubRepositories = networkGithubRepositorySearchResponse.items
                    )

                continuation.resume(networkGithubSearch)

            }
        }
    }

    private fun handleError(errorBody: ResponseBody?) {
        errorBody?.let { responseBody ->
            throw Exception(responseBody.string())
        }
    }

}
