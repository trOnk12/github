package com.example.github.data.network.api


import NetworkGithubRepositorySearchResponse
import com.example.github.data.network.model.public_repository.NetworkGithubRepository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface GithubRepositoryApi {

    @GET("/repositories")
    fun get(): Call<List<NetworkGithubRepository>>

    @GET("/search/repositories")
    fun search(@Query("q") repositoryName: String): Call<NetworkGithubRepositorySearchResponse>

    @GET
    fun fetchLink(@Url link: String): Call<List<NetworkGithubRepository>>

}