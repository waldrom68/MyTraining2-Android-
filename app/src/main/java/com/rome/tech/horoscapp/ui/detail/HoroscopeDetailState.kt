package com.rome.tech.horoscapp.ui.detail

import com.rome.tech.horoscapp.domain.model.HoroscopeModel

sealed class HoroscopeDetailState {
    data object Loading : HoroscopeDetailState()

    // Estas no es data object porque es un objete que le voy a pasas parametros
    data class Error(val error: String) : HoroscopeDetailState()
    data class Success(
        val prediction: String,
        val sign: String,
        val horoscopeModel: HoroscopeModel,
    ) : HoroscopeDetailState()

}