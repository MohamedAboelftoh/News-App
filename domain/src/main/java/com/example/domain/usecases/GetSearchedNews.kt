package com.example.domain.usecases

import com.example.domain.model.SearchResponse
import com.example.domain.repo.SearchRepo

class GetSearchedNews (private val searchRepo: SearchRepo) {
    suspend fun getSearchedNews(title : String):SearchResponse{
        return searchRepo.getNewsBySearch(title)
    }
}