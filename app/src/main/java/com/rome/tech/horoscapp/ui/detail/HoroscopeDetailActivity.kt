package com.rome.tech.horoscapp.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.rome.tech.horoscapp.R
import com.rome.tech.horoscapp.databinding.ActivityHoroscopeDetailBinding
import com.rome.tech.horoscapp.domain.model.HoroscopeInfo
import com.rome.tech.horoscapp.domain.model.HoroscopeModel.Aquarius
import com.rome.tech.horoscapp.domain.model.HoroscopeModel.Aries
import com.rome.tech.horoscapp.domain.model.HoroscopeModel.Cancer
import com.rome.tech.horoscapp.domain.model.HoroscopeModel.Capricorn
import com.rome.tech.horoscapp.domain.model.HoroscopeModel.Gemini
import com.rome.tech.horoscapp.domain.model.HoroscopeModel.Leo
import com.rome.tech.horoscapp.domain.model.HoroscopeModel.Libra
import com.rome.tech.horoscapp.domain.model.HoroscopeModel.Pisces
import com.rome.tech.horoscapp.domain.model.HoroscopeModel.Sagittarius
import com.rome.tech.horoscapp.domain.model.HoroscopeModel.Scorpio
import com.rome.tech.horoscapp.domain.model.HoroscopeModel.Taurus
import com.rome.tech.horoscapp.domain.model.HoroscopeModel.Virgo
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

        horoscopeDetailViewModel.getHoroscope(args.type)
        initUI()

    }

    private fun initUI() {
        initListeners()
        initUIState()
    }

    private fun initListeners() {
        binding.ivDetailBack.setOnClickListener { onBackPressed() }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // cuando arranque se va a enganchar con el viewModel
                horoscopeDetailViewModel.state.collect {
                    when (it) {
                        HoroscopeDetailState.Loading -> loadingState()
                        is HoroscopeDetailState.Error -> errorState()
                        is HoroscopeDetailState.Success -> successState(it)
                    }
                }
            }
        }
    }


    private fun successState(horoscopeDetailState: HoroscopeDetailState.Success) {
        binding.pbProgress.isVisible = false
        binding.tvTitle.text = horoscopeDetailState.sign
        binding.tvBodyDetail.text = horoscopeDetailState.prediction

        val image: Int = when (horoscopeDetailState.horoscopeModel) {
            Aries -> R.drawable.detail_aries
            Taurus -> R.drawable.detail_taurus
            Gemini -> R.drawable.detail_gemini
            Cancer -> R.drawable.detail_cancer
            Leo -> R.drawable.detail_leo
            Virgo -> R.drawable.detail_virgo
            Libra -> R.drawable.detail_libra
            Scorpio -> R.drawable.detail_scorpio
            Sagittarius -> R.drawable.detail_sagittarius
            Capricorn -> R.drawable.detail_capricorn
            Aquarius -> R.drawable.detail_aquarius
            Pisces -> R.drawable.detail_pisces
        }
        binding.ivDetail.setImageResource(image)
    }

    private fun errorState() {
        binding.pbProgress.isVisible = false
    }

    private fun loadingState() {
        binding.pbProgress.isVisible = true
    }

}