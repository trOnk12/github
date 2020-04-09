package com.example.github.data.network.model

import NetworkItems

data class NetworkGitHubRepositoryResponse(
    val paginationInfo: PaginationInfo,
    val networkGitHubRepositories: List<NetworkGithubRepository>
)
