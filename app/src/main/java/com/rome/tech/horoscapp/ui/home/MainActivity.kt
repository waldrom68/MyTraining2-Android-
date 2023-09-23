package com.rome.tech.horoscapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rome.tech.horoscapp.R
import com.rome.tech.horoscapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}