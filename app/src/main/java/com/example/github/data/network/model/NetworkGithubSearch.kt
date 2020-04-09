package com.example.github.data.network.model

import NetworkItems


data class NetworkGithubSearch(
    val paginationInfo: PaginationInfo,
    val networkGitHubRepositories: List<NetworkItems>
)

