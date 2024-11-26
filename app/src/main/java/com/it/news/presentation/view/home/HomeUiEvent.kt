package com.it.news.presentation.view.home

import com.it.news.domain.model.Article

sealed class HomeUiEvent(val articles: List<Article>? = null, val message: String? = null) {
    data object Loading : HomeUiEvent()
    data class Success(val articleList: List<Article>) : HomeUiEvent(articles = articleList)
    data class Failure(val errorMessage: String) : HomeUiEvent(message = errorMessage)
}