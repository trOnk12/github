package com.example.github.domain.repository

import com.example.github.domain.model.Repository

interface IGithubRepository {
    fun get(): List<Repository>
    fun get(repositoryName: String): List<Repository>
}