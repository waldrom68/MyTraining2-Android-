package com.rome.tech.horoscapp.data.core.interceptors

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val tokenManager: TokenManager) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        // este es la peticion al servicio
        val request = chain.request()
            // aqui le agrego lo necesario, por ejemplo:
            .newBuilder()
            .header(name = "Authorization", value = tokenManager.getToken())
            .build()

        // aqui le digo que lo junte con el original
        return chain.proceed(request)
    }

}

// Esto debiera relocalizarse en otra parte de la estructura del proyecto
class TokenManager @Inject constructor() {
    fun getToken(): String = "MiToken que levante del login"
}