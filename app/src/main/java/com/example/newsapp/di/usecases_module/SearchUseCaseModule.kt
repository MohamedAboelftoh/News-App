package com.example.newsapp.di.usecases_module

import com.example.domain.model.SearchResponse
import com.example.domain.repo.SearchRepo
import com.example.domain.usecases.GetSearchedNews
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SearchUseCaseModule {
    @Provides
     fun provideSearchUseCase(searchRepo : SearchRepo): GetSearchedNews{
        return GetSearchedNews(searchRepo)
    }
}