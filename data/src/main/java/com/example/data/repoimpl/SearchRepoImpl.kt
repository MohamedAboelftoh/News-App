package com.example.data.repoimpl

import com.example.data.remote.WebServices
import com.example.domain.model.SearchResponse
import com.example.domain.repo.SearchRepo

class SearchRepoImpl(private val webServices: WebServices):SearchRepo {
    override suspend fun getNewsBySearch(title: String): SearchResponse {
        return webServices.search(q = title)
    }

}