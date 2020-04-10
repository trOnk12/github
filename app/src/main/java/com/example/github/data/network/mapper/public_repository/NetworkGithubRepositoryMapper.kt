package com.example.github.data.network.mapper.public_repository

import com.example.github.core.functional.mapper.Mapper
import com.example.github.data.network.mapper.NetworkOwnerMapper
import com.example.github.data.network.model.public_repository.NetworkGithubRepository
import com.example.github.domain.model.Repository
import javax.inject.Inject

class NetworkGithubRepositoryMapper
@Inject constructor(
    private val networkOwnerMapper: NetworkOwnerMapper
) : Mapper<NetworkGithubRepository, Repository> {

    override fun map(input: NetworkGithubRepository): Repository {
        return Repository(
            id = input.id ?: -1,
            name = input.name ?: "No name",
            description = input.description ?: "No description",
            owner = networkOwnerMapper.map(input.owner)
        )
    }

}
