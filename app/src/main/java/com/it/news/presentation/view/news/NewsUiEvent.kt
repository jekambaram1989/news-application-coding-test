package com.it.news.presentation.view.news

import com.it.news.domain.model.News

sealed class NewsUiEvent(val news: List<News>? = null, val message: String? = null) {
    data object Loading : NewsUiEvent()
    data class Success(val newsList: List<News>) : NewsUiEvent(news = newsList)
    data class Failure(val errorMessage: String) : NewsUiEvent(message = errorMessage)
}