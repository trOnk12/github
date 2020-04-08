package com.example.github.data.network.repository

import com.example.github.data.network.mapper.RepositoryMapper
import com.example.github.domain.model.Repository
import com.example.github.domain.repository.IGithubRepository
import com.example.github.data.network.api.GithubRepositoryApi
import com.example.github.data.network.service.GithubRepositoryService
import javax.inject.Inject

class GithubRepository
@Inject constructor(
    private val githubRepositoryService: GithubRepositoryService,
    private val repositoryMapper: RepositoryMapper
) : IGithubRepository {

    override suspend fun get(): List<Repository> {
        return githubRepositoryService.get().networkGitHubRepositories.map {
            repositoryMapper.map(it)
        }
    }


//    override suspend fun get(): List<Repository> {
//        return githubRepositoryApi.get()
//            .map { networkGithubRepository -> repositoryMapper.map(networkGithubRepository) }
//    }
//
//    override suspend fun get(repositoryName: String): List<Repository> {
//        return githubRepositoryApi.get(repositoryName)
//            .map { networkGithubRepository -> repositoryMapper.map(networkGithubRepository) }
//    }

}

