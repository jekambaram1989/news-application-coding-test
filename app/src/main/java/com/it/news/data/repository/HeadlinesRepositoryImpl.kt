package com.it.news.data.repository

import com.it.news.data.remote.ApiService
import com.it.news.data.util.resultFlow
import com.it.news.domain.repository.HeadlinesRepository
import javax.inject.Inject

class HeadlinesRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    HeadlinesRepository {

    override suspend fun getHeadlines(country : String) =
        resultFlow {
            apiService.getHeadlines(country)
        }
}