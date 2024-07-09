package com.example.newsapp.di.usecases_module

import com.example.domain.model.NewsResponse
import com.example.domain.repo.NewsRepo
import com.example.domain.usecases.NewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NewsUseCaseModule {
    @Provides
     fun provideNewsUseCase(newsRepo: NewsRepo ) : NewsUseCase{
        return NewsUseCase(newsRepo)
    }
}