package com.it.news.domain.repository

import com.it.news.data.util.NetworkResult
import com.it.news.domain.model.Headlines
import kotlinx.coroutines.flow.Flow

interface HeadlinesRepository {
    suspend fun getHeadlines(country: String): Flow<NetworkResult<Headlines>>
}