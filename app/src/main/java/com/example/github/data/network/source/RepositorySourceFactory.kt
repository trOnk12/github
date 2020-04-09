package com.example.github.data.network.source

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.github.domain.model.Repository
import javax.inject.Inject

class RepositorySourceFactory @Inject constructor(private val pageKeyedRepositorySource: PageKeyedRepositorySource) :
    DataSource.Factory<String, Repository>() {

    private val repositories = MutableLiveData<PageKeyedRepositorySource>()

    override fun create(): DataSource<String, Repository> {
        repositories.postValue(pageKeyedRepositorySource)
        return pageKeyedRepositorySource
    }

}