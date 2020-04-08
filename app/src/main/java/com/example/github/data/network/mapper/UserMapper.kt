package com.example.github.data.network.mapper

import com.example.github.core.functional.mapper.Mapper
import com.example.github.data.network.model.NetworkOwner
import com.example.github.domain.model.User

class UserMapper : Mapper<NetworkOwner, User> {

    override fun map(input: NetworkOwner): User {
        return User(
            id = input.id ?: -1,
            name = input.login ?: "No name",
            avatarUrl = input.avatar_url ?: "No url"
        )
    }

}