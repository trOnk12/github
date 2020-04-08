package com.example.github.data.network.model

data class NetworkGitHubRepositoryResponse(
    val nextPageInfo: NextPageInfo,
    val networkGitHubRepositories: List<NetworkGithubRepository>
)

data class NextPageInfo(
    val firstLink:String?,
    val nextLink: String?,
    val previousLink: String?
)