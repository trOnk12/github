package com.example.github.data.network.service

import com.example.github.data.network.api.GithubRepositoryApi
import com.example.github.data.network.model.HeaderData
import com.example.github.data.network.model.NetworkGitHubRepositoryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject


class GithubRepositoryService
@Inject constructor(
    private val githubRepositoryApi: GithubRepositoryApi
) {

    suspend fun get(): NetworkGitHubRepositoryResponse =
        withContext(Dispatchers.IO) {
            suspendCancellableCoroutine<NetworkGitHubRepositoryResponse> { continuation ->
                val response = githubRepositoryApi.get().execute()
                if (response.isSuccessful) {
                    val headers = response.headers()

                } else {
                    response.errorBody()?.let { responseBody ->
                        throw Exception(responseBody.string())
                    }

                }
            }
        }

    suspend fun fetchLink(link: String): NetworkGitHubRepositoryResponse =
        withContext(Dispatchers.IO) {
            suspendCancellableCoroutine<NetworkGitHubRepositoryResponse> { continuation ->
                val response = githubRepositoryApi.get().execute()
                if (response.isSuccessful) {
                    val headers = response.headers()

                } else {
                    response.errorBody()?.let { responseBody ->
                        throw Exception(responseBody.string())
                    }

                }
            }
        }

}

