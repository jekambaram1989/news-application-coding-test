package com.it.news.di

import com.it.news.data.remote.ApiEndpoints
import com.it.news.data.remote.ApiService
import com.it.news.data.remote.HeaderInterceptor
import com.it.news.data.remote.interceptor
import com.it.news.data.repository.HeadlinesRepositoryImpl
import com.it.news.data.repository.NewsRepositoryImpl
import com.it.news.domain.repository.HeadlinesRepository
import com.it.news.domain.repository.NewsRepository
import com.it.news.domain.usecase.HeadLineUseCase
import com.it.news.domain.usecase.NewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesApiClient(): ApiService {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(interceptor)
            .readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS).build()
        val retroFit = Retrofit.Builder().baseUrl(ApiEndpoints.BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
        return retroFit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesHeadlinesRepository(apiService: ApiService): HeadlinesRepository {
        return HeadlinesRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun providesNewsRepository(apiService: ApiService): NewsRepository {
        return NewsRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun providesHeadLineUseCase(repository: HeadlinesRepository): HeadLineUseCase {
        return HeadLineUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesNewsUseCase(repository: NewsRepository): NewsUseCase {
        return NewsUseCase(repository)
    }
}