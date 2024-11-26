package com.it.news.data.repository

import com.it.news.data.remote.ApiService
import com.it.news.domain.model.News
import com.it.news.domain.repository.NewsRepository
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    NewsRepository {

    override suspend fun getNews(category: String): Response<News> {
        return apiService.getNews(category)
    }
}