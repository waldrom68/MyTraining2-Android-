package com.rome.tech.horoscapp.data

import android.util.Log
import com.rome.tech.horoscapp.data.network.HoroscopeApiService
import com.rome.tech.horoscapp.domain.Repository
import com.rome.tech.horoscapp.domain.model.PredictionModel
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: HoroscopeApiService) : Repository {

    override suspend fun getPrediction(sign: String): PredictionModel? {
        // el retrofit es creado e inyectado a la app desde un modulo

//        retrofit = getRetrofit()
//        // corutina fuera del hilo principal
        runCatching { apiService.getHoroscope(sign) }
            .onFailure { Log.i("PRUEBA", "ERROR ${it.message}") }
            .onSuccess { return it.toDomain() }

        return null
    }

}