package com.example.newsapp.homeFragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.LocaleList
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentSettingsBinding
import com.google.android.material.navigation.NavigationBarView
import java.util.Locale

class SettingsFragment : Fragment() {
lateinit var viewBinding : FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentSettingsBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 1) {
                    val localeListCompat = LocaleListCompat.create(Locale("en"))
                    AppCompatDelegate.setApplicationLocales(localeListCompat)
                } else if (position == 2) {
                    val localeListCompat = LocaleListCompat.create(Locale("ar"))
                    AppCompatDelegate.setApplicationLocales(localeListCompat)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }
}