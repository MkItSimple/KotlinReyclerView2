package com.example.kotlinreyclerview2

data class Video(
    val channel: Channel,
    val id: Int,
    val imageUrl: String,
    val link: String,
    val name: String,
    val numberOfViews: Int
)