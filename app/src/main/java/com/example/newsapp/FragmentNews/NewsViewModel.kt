package com.example.newsapp.FragmentNews

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.News
import com.example.domain.model.Source
import com.example.domain.model.SourcesResponse
import com.example.domain.usecases.GetSearchedNews
import com.example.domain.usecases.NewsUseCase
import com.example.domain.usecases.SourcesUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val sourcesUseCase: SourcesUseCase ,
    private val getSearchedNews: GetSearchedNews ,
     private val newsUseCase: NewsUseCase
) : ViewModel() {
    @SuppressLint("StaticFieldLeak")
    private lateinit var context: Context
    fun getContext(mContext: Context) {
        context = mContext
    }

    val shouldShowLoading = MutableLiveData<Boolean>()
    val sources = MutableLiveData<List<Source?>?>()
    val news = MutableLiveData<List<News?>?>()
    val newsQuery = MutableLiveData<List<News?>?>()
    fun getNewsSources(cat: String) {
        shouldShowLoading.postValue(true)
        viewModelScope.launch {
            try {
                val response = sourcesUseCase.getSources(cat)
                sources.postValue(response.sources)
            } catch (e: HttpException) {
                val errorBodyGsonString = e.response()?.errorBody().toString()
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
            } catch (e: Exception) {
                // viewBinding.progressBar.isVisible = false
                val dialog = AlertDialog.Builder(context)
                dialog.setMessage(e.message)
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
            } finally {
                shouldShowLoading.postValue(false)
            }
        }
    }

    fun getNews(sourceId: String?) {
        shouldShowLoading.postValue(true)
        viewModelScope.launch {
            try {
                val response = newsUseCase.getNews(sourceId!!)
                news.postValue(response.articles)
            } catch (e: HttpException) {
                val errorBodyGsonString = e.response()?.errorBody()?.string()
                val response =
                    Gson().fromJson(errorBodyGsonString, SourcesResponse::class.java)
                val dialog = AlertDialog.Builder(context)
                dialog.setMessage(response.message.toString())
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
            } catch (e: Exception) {
                shouldShowLoading.postValue(false)
                val dialog = AlertDialog.Builder(context)
                dialog.setMessage(e.message)
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
            finally {
                shouldShowLoading.postValue(false)
            }
        }

    }

    fun getNewsQuery(query: String?) {
        viewModelScope.launch {
            try {
                val response = getSearchedNews.getSearchedNews(query!!)
                newsQuery.postValue(response.articles)
            }
            catch (e : HttpException){
                val errorBodyGsonString = e.response()?.errorBody()?.string()
                val response =
                    Gson().fromJson(errorBodyGsonString, SourcesResponse::class.java)
                val dialog = AlertDialog.Builder(context)
                if (query != "") {
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
            }catch (e : Exception){
                val dialog = AlertDialog.Builder(context)
                dialog.setMessage(e.message)
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

}