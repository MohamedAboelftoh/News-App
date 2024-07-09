package com.example.newsapp.FragmentNews

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.News
import com.example.newsapp.R
import com.example.newsapp.databinding.ItemRvBinding

class Adapter(private var newsList : List<News?>?= null): RecyclerView.Adapter<Adapter.ViewHolder> (){

    class ViewHolder ( val itemBinding : ItemRvBinding): RecyclerView.ViewHolder(itemBinding.root){

    }

     @SuppressLint("SuspiciousIndentation")
     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val viewBinding = ItemRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
         return ViewHolder(viewBinding)
     }

     override fun getItemCount(): Int {
        return newsList?.size ?: 0
     }

     override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = newsList!![position]
         holder.itemBinding.title.text = news?.title
         holder.itemBinding.description.text = news?.description
         Glide.with(holder.itemView)
             .load(news?.urlToImage)
             .placeholder(R.drawable.logo)
             .into(holder.itemBinding.imag)
         holder.itemView.setOnClickListener {
             onItemClickListener?.onItemClick(position,news!!)
         }
     }

     @SuppressLint("NotifyDataSetChanged")
     fun bindNews(articles: List<News?>?) {
          newsList = articles
         notifyDataSetChanged()
     }



     var onItemClickListener : OnItemClickListener ?= null
     interface OnItemClickListener{
         fun onItemClick(position : Int , news : News)
     }
 }