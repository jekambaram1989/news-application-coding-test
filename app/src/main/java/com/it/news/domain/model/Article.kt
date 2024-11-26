package com.it.news.domain.model

data class Article(
    val source: Source,
    var author: String? = null,
    var title: String,
    var description: String? = null,
    val url: String,
    var urlToImage: String? = null,
    var publishedAt: String,
    var content: String? = null
)
