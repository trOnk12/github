package com.example.github.data.network.model.public_repository

import com.example.github.data.network.model.PaginationInfo

data class NetworkGitHubRepositoryResponse(
    val pageLinks: PaginationInfo,
    val networkGitHubRepositories: List<NetworkGithubRepository>
)
