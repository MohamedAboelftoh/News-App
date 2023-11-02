package com.example.newsapp.search

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.example.newsapp.NewsResponce.News
import com.google.gson.annotations.SerializedName

@Parcelize
data class SearchResponse(

	@field:SerializedName("totalResults")
	val totalResults: Int? = null,

	@field:SerializedName("articles")
	val articles: List<News?>? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable