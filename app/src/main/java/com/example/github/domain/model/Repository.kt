package com.example.github.domain.model

data class Repository(
    val id: Int,
    val name: String,
    val description: String,
    val owner: User
)