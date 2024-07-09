package com.example.data.repoimpl

import com.example.data.remote.WebServices
import com.example.domain.model.SourcesResponse
import com.example.domain.repo.SourcesRepo

class SourcesRepoImpl (private val webServices: WebServices) : SourcesRepo {
    override suspend fun getSources(category: String): SourcesResponse {
        return webServices.getSources(category = category)
    }
}