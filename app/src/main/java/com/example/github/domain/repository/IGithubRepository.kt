package com.example.github.domain.repository

import com.example.github.domain.model.Repository

interface IGithubRepository {
     fun getPublic(): List<Repository>
//    suspend fun get(repositoryName: String): List<Repository>
}