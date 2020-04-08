package com.example.github.core.functional.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}