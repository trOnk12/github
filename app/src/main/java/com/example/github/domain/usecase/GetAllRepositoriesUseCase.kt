package com.example.github.domain.usecase

import com.example.github.core.interactor.None
import com.example.github.core.interactor.UseCase
import com.example.github.domain.model.Repository
import com.example.github.domain.repository.IGithubRepository
import javax.inject.Inject

class GetAllRepositoriesUseCase
@Inject constructor(
    private val gitHubRepository: IGithubRepository
) : UseCase<List<Repository>, None>() {

    override suspend fun run(params: None): List<Repository> {
        return gitHubRepository.getPublic()
    }

}