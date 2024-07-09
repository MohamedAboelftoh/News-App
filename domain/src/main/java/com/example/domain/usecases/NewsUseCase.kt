package com.example.domain.usecases

import com.example.domain.model.News
import com.example.domain.model.NewsResponse
import com.example.domain.repo.NewsRepo

class NewsUseCase (private val newsRepo: NewsRepo) {
    suspend fun getNews(sourceId : String): NewsResponse{
        return newsRepo.getNewsFromRemote(sourceId)
    }
}