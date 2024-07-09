package com.example.newsapp.di.usecases_module

import com.example.domain.model.SourcesResponse
import com.example.domain.repo.SourcesRepo
import com.example.domain.usecases.SourcesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SourcesUseCaseModule {
    @Provides
     fun provideSourcesUseCase(sourcesRepo : SourcesRepo):SourcesUseCase{
        return SourcesUseCase(sourcesRepo)
    }
}