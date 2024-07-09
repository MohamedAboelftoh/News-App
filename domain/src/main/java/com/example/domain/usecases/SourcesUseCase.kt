package com.example.domain.usecases

import com.example.domain.model.News
import com.example.domain.model.NewsResponse
import com.example.domain.model.Source
import com.example.domain.model.SourcesResponse
import com.example.domain.repo.SourcesRepo

class SourcesUseCase (private val sourcesRepo: SourcesRepo){
   suspend fun getSources( category: String) : SourcesResponse{
       return sourcesRepo.getSources(category)
   }
}