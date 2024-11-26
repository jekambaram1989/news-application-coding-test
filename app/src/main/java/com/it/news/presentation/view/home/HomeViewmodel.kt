package com.it.news.presentation.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.it.news.data.remote.ApiEndpoints
import com.it.news.data.util.NetworkResult
import com.it.news.domain.usecase.HeadLineUseCase
import com.it.news.domain.usecase.NewsUseCase
import com.it.news.presentation.view.news.NewsUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewmodel @Inject constructor(
    private val headLineUseCase: HeadLineUseCase,
    private val newsUseCase: NewsUseCase,
) : ViewModel() {

    private val _headlines = MutableStateFlow<HomeUiEvent>(HomeUiEvent.Loading)
    val headlines: StateFlow<HomeUiEvent> = _headlines

    private val _news = MutableStateFlow<NewsUiEvent>(NewsUiEvent.Loading)
    val news: StateFlow<NewsUiEvent> = _news

    init {
        getHeadlines()
    }

    fun getHeadlines() {
        viewModelScope.launch(Dispatchers.IO) {
            launch {
                headLineUseCase.invoke().collect { result ->
                    when (result) {
                        is NetworkResult.Failure -> {
                            _headlines.emit(HomeUiEvent.Failure(result._message))
                        }

                        is NetworkResult.Loading -> {
                            _headlines.emit(HomeUiEvent.Loading)
                        }

                        is NetworkResult.Success -> {
                            val updated = result.data!!.articles.filter { filterItem ->
                                filterItem.source.name != null && filterItem.source.name != "[Removed]"
                            }.onEach { eachItem ->
                                eachItem.source.name = eachItem.source.name.uppercase()
                                eachItem.title = eachItem.title ?: ""
                                eachItem.description = eachItem.description ?: ""
                                eachItem.author = eachItem.author ?: ""
                                eachItem.content = eachItem.content ?: ""
                                eachItem.urlToImage =
                                    if (eachItem.urlToImage != null) eachItem.urlToImage else ApiEndpoints.IMAGE
                            }
                            _headlines.emit(HomeUiEvent.Success(updated))
                        }
                    }
                }
            }
            launch {
                newsUseCase.invoke().collect { result ->
                    when (result) {
                        is NetworkResult.Failure -> {
                            _news.emit(NewsUiEvent.Failure(result._message))
                        }

                        is NetworkResult.Loading -> {
                            _news.emit(NewsUiEvent.Loading)
                        }

                        is NetworkResult.Success -> {
                            val newsCategories = result.data!!.onEach { news ->
                                news.articles.filter { filterItem ->
                                    filterItem.source.name != null && filterItem.source.name != "[Removed]"
                                }.onEach { eachItem ->
                                    eachItem.source.name = eachItem.source.name.uppercase()
                                    eachItem.title = eachItem.title ?: ""
                                    eachItem.description = eachItem.description ?: ""
                                    eachItem.author = eachItem.author ?: ""
                                    eachItem.content = eachItem.content ?: ""
                                    eachItem.urlToImage =
                                        if (eachItem.urlToImage != null) eachItem.urlToImage else ApiEndpoints.IMAGE
                                }
                            }
                            _news.emit(NewsUiEvent.Success(newsCategories))
                        }
                    }
                }
            }
        }
    }
}
