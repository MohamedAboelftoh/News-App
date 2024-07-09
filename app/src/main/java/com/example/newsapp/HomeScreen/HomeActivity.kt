package com.example.newsapp.HomeScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.newsapp.FragmentNews.NewsFragment
import com.example.newsapp.homeFragments.CategoriesFragment
import com.example.newsapp.R
import com.example.newsapp.homeFragments.SettingsFragment
import com.example.newsapp.databinding.ActivityHomeBinding
import com.google.android.material.search.SearchView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private var categoriesFragment = CategoriesFragment()
     lateinit var viewBinding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.search.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                val fragment = supportFragmentManager.findFragmentById(R.id.container_home) as? NewsFragment
                fragment?.updateSearchQuery(newText)
                return true
            }
        })
        viewBinding.imgSearch.setOnClickListener {
            viewBinding.imgSearch.visibility = View.GONE
            viewBinding.search.visibility = View.VISIBLE
            viewBinding.textToolbar.visibility = View.GONE
            viewBinding.list.visibility = View.GONE
        }
        viewBinding.search.setOnCloseListener {
            viewBinding.textToolbar.visibility = View.VISIBLE
            viewBinding.list.visibility = View.VISIBLE
            viewBinding.imgSearch.visibility = View.VISIBLE
            viewBinding.search.visibility = View.GONE
            return@setOnCloseListener true
        }
        pushFragment(categoriesFragment)
        setNameOfCategoryInToolBar()
        //open drawer
        viewBinding.list.setOnClickListener {
            viewBinding.drawerLayout.openDrawer(GravityCompat.START)
        }
        // select setting or category
        viewBinding.navigation.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.setting ->{
               pushFragment(SettingsFragment())
                    viewBinding.drawerLayout.close()
                    viewBinding.textToolbar.setText(R.string.setting)
                    viewBinding.imgSearch.isVisible = false
                    viewBinding.search.visibility = View.GONE
                }
                R.id.category ->{
                    pushFragment(categoriesFragment)
                    viewBinding.drawerLayout.close()
                    viewBinding.textToolbar.setText(R.string.Categories)
                    viewBinding.search.visibility = View.GONE
                    viewBinding.imgSearch.isVisible = false
                }
            }
            return@setNavigationItemSelectedListener true
        }
    }


    private fun setNameOfCategoryInToolBar(){
        categoriesFragment.onCategorySportClickListener = object : CategoriesFragment.OnCategoryClickListener{
            override fun onCategoryClick() {
                viewBinding.textToolbar.setText(R.string.Sports)
             viewBinding.imgSearch.isVisible = true
            }
        }
        categoriesFragment.onCategoryScienceClickListener = object : CategoriesFragment.OnCategoryClickListener{
            override fun onCategoryClick() {
                viewBinding.textToolbar.setText(R.string.Science)
                viewBinding.imgSearch.isVisible = true
            }
        }
        categoriesFragment.onCategoryPoliticClickListener = object : CategoriesFragment.OnCategoryClickListener{
            override fun onCategoryClick() {
                viewBinding.textToolbar.setText(R.string.Politic)
                viewBinding.imgSearch.isVisible = true
            }
        }
        categoriesFragment.onCategoryBusinessClickListener = object : CategoriesFragment.OnCategoryClickListener{
            override fun onCategoryClick() {
                viewBinding.textToolbar.setText(R.string.Business)
                viewBinding.imgSearch.isVisible = true
            }
        }
        categoriesFragment.onCategoryHealthClickListener = object : CategoriesFragment.OnCategoryClickListener{
            override fun onCategoryClick() {
                viewBinding.textToolbar.setText(R.string.Health)
                viewBinding.imgSearch.isVisible = true
            }
        }
        categoriesFragment.onCategoryEnvironmentClickListener = object : CategoriesFragment.OnCategoryClickListener{
            override fun onCategoryClick() {
                viewBinding.textToolbar.setText(R.string.Environment)
                viewBinding.imgSearch.isVisible = true
            }
        }
    }
   private fun pushFragment(fragment : Fragment){
       supportFragmentManager.beginTransaction()
           .replace(R.id.container_home, fragment)
           .commit()
   }
}