package com.it.news.domain.repository

import com.it.news.domain.model.News
import retrofit2.Response


interface NewsRepository {
    suspend fun getNews(category: String): Response<News>
}