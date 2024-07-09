package com.example.newsapp.di.repo_module

import com.example.data.remote.WebServices
import com.example.data.repoimpl.SearchRepoImpl
import com.example.domain.repo.SearchRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SearchRepoModule {
    @Provides
    fun provideSearchRepo(webServices: WebServices) : SearchRepo{
        return SearchRepoImpl(webServices)
    }
}