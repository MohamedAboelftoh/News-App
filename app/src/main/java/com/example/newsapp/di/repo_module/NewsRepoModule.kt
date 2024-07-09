package com.example.newsapp.di.repo_module

import com.example.data.remote.WebServices
import com.example.data.repoimpl.NewsRepoImpl
import com.example.domain.repo.NewsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent ::class)
object NewsRepoModule {
    @Provides
    fun provideNewsRepo(webServices: WebServices ) :NewsRepo{
        return NewsRepoImpl(webServices)
    }
}