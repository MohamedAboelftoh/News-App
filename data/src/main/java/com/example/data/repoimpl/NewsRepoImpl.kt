package com.example.data.repoimpl
import com.example.data.remote.WebServices
import com.example.domain.model.NewsResponse
import com.example.domain.repo.NewsRepo

class NewsRepoImpl (private val webServices: WebServices  ): NewsRepo {
    override suspend fun getNewsFromRemote(sourceId: String): NewsResponse {
        return webServices.getNews(sourceId = sourceId)
    }
}