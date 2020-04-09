package com.example.github.di.module.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dog.snow.androidrecruittest.di.module.viewmodel.ViewModelFactory
import dog.snow.androidrecruittest.di.module.viewmodel.ViewModelKey
import com.example.github.feature.repositorylist.GithubRepositoryListViewModel


@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(GithubRepositoryListViewModel::class)
    internal abstract fun listFragmentViewModel(githubRepository: GithubRepositoryListViewModel): ViewModel
}
