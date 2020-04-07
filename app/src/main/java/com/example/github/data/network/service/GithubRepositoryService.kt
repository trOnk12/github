package com.example.github.data.network.service

import NetworkGithubRepository
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubRepositoryService {

    @GET("users/{id}")
    suspend fun get(@Path("name") name: String): List<NetworkGithubRepository>

    @GET()
    suspend fun get(): List<NetworkGithubRepository>

}