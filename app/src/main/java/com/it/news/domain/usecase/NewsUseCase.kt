package com.it.news.domain.usecase

import com.it.news.data.util.NetworkResult
import com.it.news.domain.model.News
import com.it.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsUseCase @Inject constructor(private val repository: NewsRepository) {

    private val categories =
        listOf("business", "Entertainment", "technology")

    suspend operator fun invoke(): Flow<NetworkResult<List<News>>> = flow {
        emit(NetworkResult.Loading())
        val list = mutableListOf<News>()
        categories.forEach { category ->
            val result = repository.getNews(category)
            if (result.isSuccessful) {
                val successData = result.body()!!
                successData.country = category.replaceFirstChar(Char::titlecase)
                list.add(successData)
            } else {
                emit(NetworkResult.Failure("Data not available"))
                return@flow
            }
        }
        emit(NetworkResult.Success(list))
    }
}