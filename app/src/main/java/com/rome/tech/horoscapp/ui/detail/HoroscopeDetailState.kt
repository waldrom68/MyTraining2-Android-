package com.rome.tech.horoscapp.ui.detail

sealed class HoroscopeDetailState {
    data object Loading:HoroscopeDetailState()
    // Estas no es data object porque es un objete que le voy a pasas parametros
    data class Error(val error:String):HoroscopeDetailState()
    data class Success(val data:String):HoroscopeDetailState()

}