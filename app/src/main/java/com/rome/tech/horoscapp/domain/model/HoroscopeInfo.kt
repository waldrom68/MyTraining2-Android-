package com.rome.tech.horoscapp.domain.model

import com.rome.tech.horoscapp.R

sealed class HoroscopeInfo(val img:Int, val name:Int) {

    data object Aries : HoroscopeInfo(R.drawable.aries, R.string.aries)
    data object Taurus : HoroscopeInfo(R.drawable.taurus, R.string.taurus)
    data object Gemini : HoroscopeInfo(R.drawable.gemini, R.string.gemini)
    data object Cancer : HoroscopeInfo(R.drawable.cancer, R.string.cancer)
    data object Leo : HoroscopeInfo(R.drawable.leo, R.string.leo)
    data object Virgo : HoroscopeInfo(R.drawable.aries, R.string.aries)
    data object Libra : HoroscopeInfo(R.drawable.libra, R.string.libra)
    data object Scorpius : HoroscopeInfo(R.drawable.scorpius, R.string.scorpius)
    data object Sagittarius : HoroscopeInfo(R.drawable.sagittarius, R.string.sagittarius)
    data object Capricornus : HoroscopeInfo(R.drawable.capricornus, R.string.capricornus)
    data object Aquarius : HoroscopeInfo(R.drawable.aquarius, R.string.aquarius)
    data object Pisces : HoroscopeInfo(R.drawable.pisces, R.string.pisces)

}
