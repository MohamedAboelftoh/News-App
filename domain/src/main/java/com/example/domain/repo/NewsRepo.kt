package com.example.domain.repo

import com.example.domain.model.NewsResponse

interface NewsRepo {
    suspend fun getNewsFromRemote(sourceId : String) : NewsResponse

}