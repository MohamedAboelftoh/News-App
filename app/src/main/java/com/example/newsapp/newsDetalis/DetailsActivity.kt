package com.example.newsapp.newsDetalis

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.newsapp.NewsResponce.News
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    lateinit var viewBinding : ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        val sourceDetails = intent.getStringExtra("url")
        viewBinding.openSource.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(sourceDetails))
            this.startActivity(intent)
        }
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val imgURL = intent.getStringExtra("img")
        val sourceName = intent.getStringExtra("sourceName")
        val time = intent.getStringExtra("timePublish")
        Glide.with(this)
            .load(imgURL)
            .placeholder(R.drawable.logo)
            .into(viewBinding.imageView)
        viewBinding.title.text = title
        viewBinding.description.text = description
        viewBinding.sourceName.text = sourceName
        viewBinding.time.text = time
    }
}