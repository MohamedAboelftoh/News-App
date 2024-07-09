package com.example.domain.repo

import com.example.domain.model.SourcesResponse

interface SourcesRepo {
   suspend fun getSources(category : String) : SourcesResponse
}