package com.example.domain.repo

import com.example.domain.model.SearchResponse

interface SearchRepo {
    suspend fun getNewsBySearch(title : String) : SearchResponse
}