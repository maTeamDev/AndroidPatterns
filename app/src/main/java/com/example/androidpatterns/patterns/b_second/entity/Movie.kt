package com.example.androidpatterns.patterns.b_second.entity

data class Movie(
    val id: Int,
    val name: String,
    val year: Int,
    val genre: Genre,
    val serverName: String,
    val downloadTimestamp: Long
)