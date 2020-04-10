package com.example.github.data.network.mapper.search

import NetworkItems
import com.example.github.core.functional.mapper.Mapper
import com.example.github.data.network.mapper.NetworkOwnerMapper
import com.example.github.domain.model.Repository
import javax.inject.Inject

class NetworkItemMapper @Inject constructor(private val networkOwnerMapper: NetworkOwnerMapper) :
    Mapper<NetworkItems, Repository> {

    override fun map(input: NetworkItems): Repository {
        return Repository(
            id = input.id,
            name = input.name,
            description = input.description,
            owner = networkOwnerMapper.map(input.owner)
        )
    }

}