package com.example.github.di.component

import androidx.lifecycle.ViewModelProvider
import com.example.github.data.network.repository.GithubRepository
import com.example.github.di.module.ContextModule
import com.example.github.di.module.NetworkModule
import com.example.github.di.module.RepositoryModule
import com.example.github.di.module.viewmodel.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class, ViewModelModule::class, NetworkModule::class, RepositoryModule::class])
interface CoreComponent {
    fun viewModelProviderFactory(): ViewModelProvider.Factory

    fun githubRepository(): GithubRepository

}