package com.example.github.domain.usecase

import com.example.github.core.interactor.UseCase
import com.example.github.domain.model.Repository
import com.example.github.domain.repository.IGithubRepository
import javax.inject.Inject

//class GetRepositoryByNameUseCase
//@Inject constructor(
//    private val repository: IGithubRepository
//) : UseCase<List<Repository>, RepositoryName>() {
//
//    override suspend fun run(params: RepositoryName): List<Repository> {
//        return repository.get(params.value)
//    }
//
//}
//
//data class RepositoryName(val value: String)