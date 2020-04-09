package com.example.github.feature.list

import androidx.lifecycle.viewModelScope
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope


@Module
class ListFragmentModule {

    @Provides
    fun provideViewModelScope(listFragmentViewModel: ListFragmentViewModel): CoroutineScope {
        return listFragmentViewModel.viewModelScope
    }

}