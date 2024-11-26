package com.it.news.domain.model

data class Source(
    val id: String,
    var name: String,
    val description: String,
    val url: String,
    val category: String,
    val language: String,
    val country: String
)