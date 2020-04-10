package com.example.github.data.network.mapper.search

import com.example.github.core.functional.mapper.Mapper
import com.example.github.data.network.model.search.NetworkGithubSearch
import com.example.github.domain.model.Repository
import javax.inject.Inject

class NetworkGithubSearchMapper
@Inject constructor(
    private val networkItemMapper: NetworkItemMapper
) : Mapper<NetworkGithubSearch, List<Repository>> {

    override fun map(input: NetworkGithubSearch): List<Repository> {
        input.networkGitHubRepositories
        return networkItemMapper.map(input.networkGitHubRepositories)
    }

}