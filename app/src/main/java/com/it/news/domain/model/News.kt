package com.it.news.domain.model

data class News(
    val status: String,
    val totalResults: Long,
    val articles: List<Article>,
    var country: String
)