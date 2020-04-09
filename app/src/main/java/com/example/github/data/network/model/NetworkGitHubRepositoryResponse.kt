package com.example.github.data.network.model

data class NetworkGitHubRepositoryResponse(
    val paginationInfo: PaginationInfo,
    val networkGitHubRepositories: List<NetworkGithubRepository>
)

data class PaginationInfo(
    val firstLink:String?,
    val nextLink: String?,
    val previousLink: String?
)