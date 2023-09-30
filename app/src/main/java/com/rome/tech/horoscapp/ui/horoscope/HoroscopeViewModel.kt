package com.rome.tech.horoscapp.ui.horoscope

import androidx.lifecycle.ViewModel
import com.rome.tech.horoscapp.data.provider.HoroscopeProvider
import com.rome.tech.horoscapp.domain.model.HoroscopeInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HoroscopeViewModel @Inject constructor(
    private val horoscopeProvider: HoroscopeProvider,
) : ViewModel() {
//    Este codigo para obtener los datos debe evitarse, pues estamos usando inyeccion de
//    dependencias y tengo que desacoplarlo del provider. En esta parte sólo se que necesito
//    y no debo saber de como se obtiene lo que necesito
//    por tal motivo en el contructor de la inyeccion pido lo que necesito
//        private val provider: HoroscopeProvider = HoroscopeProvider()
//        private val listado: List<HoroscopeInfo> = provider.getHoroscopes()

    private var _horoscope = MutableStateFlow<List<HoroscopeInfo>>(emptyList())
    val horoscope: StateFlow<List<HoroscopeInfo>> = _horoscope

    init {
        _horoscope.value = horoscopeProvider.getHoroscopes()
    }

}

