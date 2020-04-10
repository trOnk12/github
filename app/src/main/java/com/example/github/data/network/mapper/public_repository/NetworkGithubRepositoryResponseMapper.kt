package com.example.github.data.network.mapper.public_repository

import com.example.github.core.functional.mapper.Mapper
import com.example.github.data.network.mapper.public_repository.NetworkGithubRepositoryListMapper
import com.example.github.data.network.model.public_repository.NetworkGitHubRepositoryResponse
import com.example.github.domain.model.Repository
import javax.inject.Inject

class NetworkGithubRepositoryResponseMapper
@Inject constructor(
    private val networkGithubRepositoryListMapper: NetworkGithubRepositoryListMapper
) : Mapper<NetworkGitHubRepositoryResponse, List<Repository>> {

    override fun map(input: NetworkGitHubRepositoryResponse): List<Repository> {
        return networkGithubRepositoryListMapper.map(input.networkGitHubRepositories)
    }

}