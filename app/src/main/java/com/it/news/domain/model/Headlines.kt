package com.it.news.domain.model
data class Headlines(
    val status: String,
    val totalResults: Long,
    val articles: List<Article>,
)
