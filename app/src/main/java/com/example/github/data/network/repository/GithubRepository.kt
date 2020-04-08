package com.example.github.data.network.repository

import com.example.github.domain.model.Repository
import com.example.github.domain.repository.IGithubRepository
import com.example.github.data.network.service.GithubRepositoryService
import com.example.github.domain.model.User
import javax.inject.Inject

class GithubRepository
@Inject constructor(
    private val githubRepositoryService: GithubRepositoryService
) : IGithubRepository {

    override suspend fun get(): List<Repository> {
        return githubRepositoryService.get().map { networkGithubRepository ->
            Repository(
                id = networkGithubRepository.id,
                name = networkGithubRepository.name ?: "No name",
                description = networkGithubRepository.description ?: "No description",
                owner = User(
                    id = networkGithubRepository.owner.id,
                    name = networkGithubRepository.owner.login,
                    avatarUrl = networkGithubRepository.owner.avatar_url
                )
            )
        }
    }

    override suspend fun get(repositoryName: String): List<Repository> {
        return githubRepositoryService.get(repositoryName).map { networkGithubRepository ->
            Repository(
                id = networkGithubRepository.id,
                name = networkGithubRepository.name ?: "No name",
                description = networkGithubRepository.description ?: "No description",
                owner = User(
                    id = networkGithubRepository.owner.id,
                    name = networkGithubRepository.owner.login,
                    avatarUrl = networkGithubRepository.owner.avatar_url
                )
            )
        }
    }

}

