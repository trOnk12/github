package com.example.github.data.network.mapper

import com.example.github.core.functional.mapper.ListMapper
import com.example.github.data.network.model.NetworkGithubRepository
import com.example.github.domain.model.Repository
import javax.inject.Inject

class RepositoryListMapper
@Inject constructor(
    private val repositoryMapper: RepositoryMapper
) : ListMapper<NetworkGithubRepository, Repository> {

    override fun map(input: List<NetworkGithubRepository>): List<Repository> {
        return input.map { repository ->  repositoryMapper.map(repository) }
    }

}