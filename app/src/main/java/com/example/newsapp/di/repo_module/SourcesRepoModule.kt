package com.example.newsapp.di.repo_module

import com.example.data.remote.WebServices
import com.example.data.repoimpl.SourcesRepoImpl
import com.example.domain.repo.SourcesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SourcesRepoModule {
    @Provides
    fun provideSourcesRepo(webServices: WebServices) : SourcesRepo {
        return SourcesRepoImpl(webServices)
    }
}