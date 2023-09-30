package com.rome.tech.horoscapp.domain

import com.rome.tech.horoscapp.domain.model.PredictionModel

// Para mantener una division de capas, esta interface será quien
interface Repository {
    suspend fun getPrediction(sign: String): PredictionModel?
}