package com.example.github.data.network.mapper

import com.example.github.core.functional.mapper.Mapper
import com.example.github.data.network.model.NetworkGitHubRepositoryResponse
import com.example.github.data.network.model.NetworkGithubSearch
import com.example.github.domain.model.Repository
import javax.inject.Inject

class NetworkGithubSearchResponseMapper
@Inject constructor(
    private val networkGithubRepositoryListMapper: NetworkGithubRepositoryListMapper
) : Mapper<NetworkGithubSearch, List<Repository>> {

    override fun map(input: NetworkGithubSearch): List<Repository> {
        return networkGithubRepositoryListMapper.map(input.networkGitHubRepositories)
    }

}