package com.example.github.data.network.model.search

import NetworkItems
import com.example.github.data.network.model.PaginationInfo


data class NetworkGithubSearch(
    val paginationInfo: PaginationInfo,
    val networkGitHubRepositories: List<NetworkItems>
)

