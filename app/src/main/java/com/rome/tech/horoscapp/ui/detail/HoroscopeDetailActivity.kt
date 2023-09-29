package com.rome.tech.horoscapp.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.rome.tech.horoscapp.databinding.ActivityHoroscopeDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHoroscopeDetailBinding

    // Para acceder al view model
    private val horoscopeDetailViewModel: HoroscopeDetailViewModel by viewModels()

    // Para capturar el argumento enviado (definido en navigation
    private val args: HoroscopeDetailActivityArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHoroscopeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Para navagar al detail, se agrega al build.gradle del proyecto y la app
        // agregando una nueva dependencia para navegacion segura
        // id("androidx.navigation.safeargs.kotlin") y se agrega vinculo en
        // el res/navigation

        initUI()


    }

    private fun initUI() {

        initUIState()
    }
    private fun initListeners() {
        binding.ivDetailBack.setOnClickListener{

        }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // cuando arranque se va a enganchar con el viewModel
                horoscopeDetailViewModel.state.collect {
                    when (it) {
                        HoroscopeDetailState.Loading -> loadingState()
                        is HoroscopeDetailState.Error -> errorState()
                        is HoroscopeDetailState.Success -> successState()
                    }
                }
            }
        }
    }



    private fun successState() {
        binding.pbProgress.isVisible = false
    }

    private fun errorState() {
        binding.pbProgress.isVisible = false
    }

    private fun loadingState() {
        binding.pbProgress.isVisible = true
        binding.tvTitle.text = args.type.name
    }
}