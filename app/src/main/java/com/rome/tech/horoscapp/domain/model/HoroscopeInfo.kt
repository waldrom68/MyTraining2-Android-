package com.rome.tech.horoscapp.domain.model

import com.rome.tech.horoscapp.R

sealed class HoroscopeInfo(val img:Int, val name:Int) {

    object Aries : HoroscopeInfo(R.drawable.aries, R.string.aries)
    object Taurus : HoroscopeInfo(R.drawable.taurus, R.string.taurus)
    object Gemini : HoroscopeInfo(R.drawable.gemini, R.string.gemini)
    object Cancer : HoroscopeInfo(R.drawable.cancer, R.string.cancer)
    object Leo : HoroscopeInfo(R.drawable.leo, R.string.leo)
    object Virgo : HoroscopeInfo(R.drawable.aries, R.string.aries)
    object Libra : HoroscopeInfo(R.drawable.libra, R.string.libra)
    object Scorpius : HoroscopeInfo(R.drawable.scorpius, R.string.scorpius)
    object Sagittarius : HoroscopeInfo(R.drawable.sagittarius, R.string.sagittarius)
    object Capricornus : HoroscopeInfo(R.drawable.capricornus, R.string.capricornus)
    object Aquarius : HoroscopeInfo(R.drawable.aquarius, R.string.aquarius)
    object Pisces : HoroscopeInfo(R.drawable.pisces, R.string.pisces)

}
