package com.example.github.data.network.model

data class NetworkGitHubRepositoryResponse(
    val headerData: HeaderData,
    val networkGitHubRepositories: List<NetworkGithubRepository>
)

data class HeaderData(
    val nextLink: String,
    val previousLink: String
)