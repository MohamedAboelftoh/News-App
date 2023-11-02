package com.example.newsapp.FragmentNews

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Application
import android.content.Context
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.Constant.Constant
import com.example.newsapp.Modle.ApiManager
import com.example.newsapp.Modle.Source
import com.example.newsapp.Modle.SourcesResponse
import com.example.newsapp.NewsResponce.News
import com.example.newsapp.NewsResponce.NewsResponse
import com.example.newsapp.search.SearchResponse
import com.google.gson.Gson

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class NewsViewModel : ViewModel() {
    @SuppressLint("StaticFieldLeak")
    private lateinit var context: Context
    fun getContext(mContext: Context){
                  context = mContext
        }
        val shouldShowLoading = MutableLiveData<Boolean>()
    val sources = MutableLiveData<List<Source?>?>()
    val news = MutableLiveData<List<News?>?>()
    val newsQuery = MutableLiveData<List<News?>?>()
    fun getNewsSources(cat : String) {
       shouldShowLoading.postValue(true)
       // viewBinding.progressBar.isVisible = true
        ApiManager.getApi()
            .getSources(Constant.ApiKey, cat)
            .enqueue(object : Callback<SourcesResponse> {
                @SuppressLint("SuspiciousIndentation")
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                   // viewBinding.progressBar.isVisible = false
                    shouldShowLoading.postValue(false)
                    if (response.isSuccessful) {
                    //    bindTabs(response.body()?.sources)
                        sources.postValue(response.body()?.sources)
                    } else {
                        val errorBodyGsonString = response.errorBody()?.string()
                        val response =
                            Gson().fromJson(errorBodyGsonString, SourcesResponse::class.java)
                        val dialog = AlertDialog.Builder(context)
                            dialog.setMessage(response.message.toString())
                                .setPositiveButton(
                                    "Try Again"
                                ) { p0, p1 ->
                                    p0?.dismiss()
                                    getNewsSources(cat)
                                }
                                .setNegativeButton(
                                    "Cancel"
                                ) { p0, p1 -> p0?.dismiss() }
                                .show()

                    }
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                   // viewBinding.progressBar.isVisible = false
                    shouldShowLoading.postValue(false)
                    val dialog = AlertDialog.Builder(context)
                    dialog.setMessage(t.message)
                        .setPositiveButton(
                            "Try Again"
                        ) { p0, p1 ->
                            p0?.dismiss()
                            getNewsSources(cat)
                        }
                        .setNegativeButton(
                            "Cancel"
                        ) { p0, p1 -> p0?.dismiss() }
                        .show()
                }

            })

    }

    fun getNews(sourceId: String?) {
        //viewBinding.progressBar.isVisible = true
        shouldShowLoading.postValue(true)
        ApiManager
            .getApi()
            .getNews(apiKey = Constant.ApiKey , sources = sourceId ?: "" )
            .enqueue(object : Callback<NewsResponse>{
                override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                    //viewBinding.progressBar.isVisible = false
                    shouldShowLoading.postValue(false)
                    if (response.isSuccessful) {
                       // adapter.bindNews(response.body()?.articles)
                        news.postValue(response.body()?.articles)
                    } else
                    {
                        val errorBodyGsonString = response.errorBody()?.string()
                        val response =
                            Gson().fromJson(errorBodyGsonString, SourcesResponse::class.java)
                        val dialog = AlertDialog.Builder(context)
                        dialog.setMessage(response.message.toString())
                            .setPositiveButton("Try Again"
                            ) { p0, p1 ->
                                p0?.dismiss()
                                getNews(sourceId)
                            }
                            .setNegativeButton("Cancel"
                            ) { p0, p1 -> p0?.dismiss() }
                            .show()
                    }
                }
                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    //viewBinding.progressBar.isVisible = false
                    shouldShowLoading.postValue(false)
                    val dialog = AlertDialog.Builder(context)
                    dialog.setMessage(t.message)
                        .setPositiveButton(
                            "Try Again"
                        ) { p0, p1 ->
                            p0?.dismiss()
                            getNews(sourceId)
                        }
                        .setNegativeButton(
                            "Cancel"
                        ) { p0, p1 -> p0?.dismiss() }
                        .show()
                }

            })
    }
    fun getNewsQuery(query: String?) {
        ApiManager.getApi()
            .search(Constant.ApiKey,query!!)
            .enqueue(object :Callback<SearchResponse>{
                @SuppressLint("SuspiciousIndentation")
                override fun onResponse(
                    call: Call<SearchResponse>,
                    response: Response<SearchResponse>
                ) {
                    if (response.isSuccessful) {
                        //adapter.bindNews(response.body()?.articles)
                        newsQuery.postValue(response.body()?.articles)
                    } else {
                        val errorBodyGsonString = response.errorBody()?.string()
                        val response =
                            Gson().fromJson(errorBodyGsonString, SourcesResponse::class.java)
                        val dialog = AlertDialog.Builder(context)
                        if(query != "") {
                            dialog.setMessage(response.message.toString())
                                .setPositiveButton(
                                    "Try Again"
                                ) { p0, p1 ->
                                    p0?.dismiss()
                                    getNewsQuery(query)
                                }
                                .setNegativeButton(
                                    "Cancel"
                                ) { p0, p1 -> p0?.dismiss() }
                                .show()
                        }
                    }
                }

                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    val dialog = AlertDialog.Builder(context)
                    dialog.setMessage(t.message)
                        .setPositiveButton(
                            "Try Again"
                        ) { p0, p1 ->
                            p0?.dismiss()
                            getNewsQuery(query)
                        }
                        .setNegativeButton(
                            "Cancel"
                        ) { p0, p1 -> p0?.dismiss() }
                        .show()
                }

            })
    }

}