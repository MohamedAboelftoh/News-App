package com.example.newsapp.homeFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsapp.FragmentNews.NewsFragment
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentCategoriesBinding

class CategoriesFragment : Fragment() {
    private lateinit var viewBinding : FragmentCategoriesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentCategoriesBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       initViews()
    }

   private fun pushFragment(fragment : Fragment){
       parentFragmentManager.beginTransaction()
           .replace(R.id.container_home,fragment)
           .commit()
   }

    private fun initViews(){
        viewBinding.sports.setOnClickListener {
            onCategorySportClickListener?.onCategoryClick()
            sendDataToFragmentNews(R.id.sports)
        }
        viewBinding.business.setOnClickListener {
            onCategoryBusinessClickListener?.onCategoryClick()
           sendDataToFragmentNews(R.id.business)
        }
        viewBinding.environment.setOnClickListener {
            onCategoryEnvironmentClickListener?.onCategoryClick()
            sendDataToFragmentNews(R.id.environment)
        }
        viewBinding.health.setOnClickListener {
            onCategoryHealthClickListener?.onCategoryClick()
           sendDataToFragmentNews(R.id.health)
        }
        viewBinding.politics.setOnClickListener {
            onCategoryPoliticClickListener?.onCategoryClick()
           sendDataToFragmentNews(R.id.politics)
        }
        viewBinding.science.setOnClickListener {
            onCategoryScienceClickListener?.onCategoryClick()
            sendDataToFragmentNews(R.id.science)
        }
    }

    private fun sendDataToFragmentNews(id: Int) {
        val bundle = Bundle()
        bundle.putInt("id",id)
        val fragment = NewsFragment()
        fragment.arguments = bundle
        pushFragment(fragment)
    }
    var onCategoryScienceClickListener : OnCategoryClickListener?= null
    var onCategoryPoliticClickListener : OnCategoryClickListener?= null
    var onCategoryHealthClickListener : OnCategoryClickListener?= null
    var onCategoryEnvironmentClickListener : OnCategoryClickListener?= null
     var onCategoryBusinessClickListener : OnCategoryClickListener?= null
    var onCategorySportClickListener : OnCategoryClickListener?= null
    interface OnCategoryClickListener{
        fun onCategoryClick()
    }

}