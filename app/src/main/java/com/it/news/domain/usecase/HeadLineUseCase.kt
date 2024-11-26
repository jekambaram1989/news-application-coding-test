package com.it.news.domain.usecase

import com.it.news.data.util.NetworkResult
import com.it.news.domain.model.Headlines
import com.it.news.domain.repository.HeadlinesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HeadLineUseCase @Inject constructor(private val repository: HeadlinesRepository) {
    suspend operator fun invoke(): Flow<NetworkResult<Headlines>> {
        return repository.getHeadlines("us")
    }
}