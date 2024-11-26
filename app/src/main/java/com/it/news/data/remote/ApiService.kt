package com.it.news.data.remote

import com.it.news.domain.model.Headlines
import com.it.news.domain.model.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(ApiEndpoints.NEWS)
    suspend fun getHeadlines(@Query("country") country: String): Response<Headlines>

    @GET(ApiEndpoints.NEWS)
    suspend fun getNews(@Query("category") category: String): Response<News>
}