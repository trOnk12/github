package com.example.github.data.network.api


import com.example.github.data.network.model.NetworkGithubRepository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface GithubRepositoryApi {

    @GET("/repositories")
    fun get(): Call<List<NetworkGithubRepository>>

    @GET
    fun fetchLink(@Url link: String) : Call<List<NetworkGithubRepository>>

}