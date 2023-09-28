package com.rome.tech.horoscapp.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.rome.tech.horoscapp.databinding.ActivityHoroscopeDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHoroscopeDetailBinding

    // Para acceder al view model
    private val horoscopeDetailViewModel: HoroscopeDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHoroscopeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Para navagar al detail, se agrega al build.gradle del proyecto y la app
        // agregando una nueva dependencia para navegacion segura
        // id("androidx.navigation.safeargs.kotlin") y se agrega vinculo en
        // el res/navigation

    }
}