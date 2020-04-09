package com.example.github.data.network.source

import androidx.paging.PageKeyedDataSource
import com.example.github.data.network.repository.GithubRepository
import com.example.github.domain.model.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryPageKeyedSource
@Inject constructor(
    private val githubRepository: GithubRepository)
    : PageKeyedDataSource<String, List<Repository>>() {

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, List<Repository>>
    ) {
        val items = githubRepository.getPublic()
        callback.onResult(items)
    }

    override fun loadAfter(
        params: LoadParams<String>,
        callback: LoadCallback<String, List<Repository>>
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<String, List<Repository>>
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}