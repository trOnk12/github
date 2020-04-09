package com.example.github.data.network.mapper

import com.example.github.core.functional.mapper.ListMapper
import com.example.github.core.functional.mapper.Mapper
import com.example.github.data.network.model.NetworkGithubRepository
import com.example.github.domain.model.Repository
import javax.inject.Inject

class NetworkGithubRepositoryListMapper
@Inject constructor(
    private val networkGithubRepositoryMapper: NetworkGithubRepositoryMapper
) : ListMapper<NetworkGithubRepository, Repository> {

    override fun map(input: List<NetworkGithubRepository>): List<Repository> {
        return input.map { networkGithubRepository ->
            networkGithubRepositoryMapper.map(
                networkGithubRepository
            )
        }
    }

}
