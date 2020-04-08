package com.example.github.data.network.api


import com.example.github.data.network.model.NetworkGithubRepository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubRepositoryApi {

    @GET("users/{id}")
     fun get(@Path("name") name: String): Call<List<NetworkGithubRepository>>

    @GET("/repositories")
    fun get(): Call<List<NetworkGithubRepository>>

}