package com.example.github.di.module

import com.example.github.data.network.repository.GithubRepository
import com.example.github.domain.repository.IGithubRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideUseRepository(githubRepository: GithubRepository): IGithubRepository

}