package com.example.newsapp.Modle

import com.example.newsapp.NewsResponce.NewsResponse
import com.example.newsapp.search.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {
    @GET("v2/top-headlines/sources")
    fun getSources(@Query("apiKey") apiKey : String ,
                   @Query("category") category : String
    ) : Call<SourcesResponse>


    @GET("v2/everything")
    fun getNews(
        @Query("apiKey") apiKey : String ,
        @Query("sources") sources : String
    ) : Call<NewsResponse>

    @GET("v2/everything")
    fun search(
        @Query("apiKey") apiKey : String ,
        @Query("q") q : String
    ) : Call<SearchResponse>
}