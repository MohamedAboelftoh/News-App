package com.example.newsapp.FragmentNews

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.domain.model.News
import com.example.domain.model.Source
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.newsDetalis.DetailsActivity
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class NewsFragment : Fragment()  {
private lateinit var viewBinding : FragmentNewsBinding
lateinit var source: Source
private lateinit var viewModel : NewsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentNewsBinding.inflate(inflater,container,false)
        return viewBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[NewsViewModel ::class.java]
        viewModel.getContext(this.requireContext())
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = this.arguments
        val inputData = args?.getInt("id")
        initViews()
        initObserves()
        if(inputData == R.id.sports){
            viewModel.getNewsSources("sports")
        }
        else if(inputData == R.id.business){
            viewModel.getNewsSources("business")
        }
        else if(inputData == R.id.science){
            viewModel.getNewsSources("science")
        }
        else if(inputData == R.id.health){
            viewModel.getNewsSources("health")
        }
        else if(inputData == R.id.environment){
            viewModel.getNewsSources("entertainment")
        }
        else if(inputData == R.id.politics){
            viewModel.getNewsSources("general")
        }
        viewBinding.tabLayout.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                source =  tab?.tag as Source
                viewModel.getNews(source.id)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                 source =  tab?.tag as Source
                viewModel.getNews(source.id)
            }

        }
        )
        viewBinding.tabLayout.getTabAt(0)?.select()
    }

    private fun initObserves() {
      viewModel.shouldShowLoading.observe(viewLifecycleOwner
      ) { value -> viewBinding.progressBar.isVisible = value }
        viewModel.sources.observe(viewLifecycleOwner){
            bindTabs(it)
        }
        viewModel.news.observe(viewLifecycleOwner){
            adapter.bindNews(it)
        }
        viewModel.newsQuery.observe(viewLifecycleOwner){
            adapter.bindNews(it)
        }
    }
    private var adapter = Adapter()
    private fun initViews() {
        viewBinding.recyclerView.adapter = adapter
        adapter.onItemClickListener = object : Adapter.OnItemClickListener{
            @SuppressLint("SuspiciousIndentation")
            override fun onItemClick(position: Int, news: News) {
          val intent = Intent(context,DetailsActivity::class.java)
                intent.putExtra("title",news.title)
                intent.putExtra("description",news.description)
                intent.putExtra("img",news.urlToImage)
                intent.putExtra("sourceName",news.source?.name)
                intent.putExtra("timePublish",news.publishedAt)
                intent.putExtra("url",news.url)
                startActivity(intent)
            }
        }
    }

    private fun bindTabs(sources: List<Source?>?) {
        if(sources == null)return
        sources.forEach {source ->
            val tab = viewBinding.tabLayout.newTab()
            tab.text = source?.name
            tab.tag = source
            viewBinding.tabLayout.addTab(tab)
        }
    }
    private var searchQuery: String? = null
    fun updateSearchQuery(newText: String?) {
        searchQuery = newText
        updateSearchResults()
    }
    private fun updateSearchResults() {
        if(searchQuery != "")
        viewModel.getNewsQuery(searchQuery)
        else
            viewModel.getNews(source.id)
    }
}