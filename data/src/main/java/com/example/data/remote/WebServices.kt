package com.example.data.remote

import com.example.data.Constant.Constant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {
    @GET("v2/top-headlines/sources")
    suspend fun getSources(@Query("apiKey") apiKey : String?= Constant.ApiKey,
                   @Query("category") category : String
    ) : com.example.domain.model.SourcesResponse


    @GET("v2/everything")
    suspend fun getNews(
        @Query("apiKey") apiKey : String ?= Constant.ApiKey,
        @Query("sources") sourceId : String
    ) : com.example.domain.model.NewsResponse

    @GET("v2/everything")
    suspend fun search(
        @Query("apiKey") apiKey : String?= Constant.ApiKey ,
        @Query("q") q : String
    ) : com.example.domain.model.SearchResponse
}