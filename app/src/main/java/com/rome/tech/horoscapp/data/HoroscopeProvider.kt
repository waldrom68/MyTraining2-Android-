package com.rome.tech.horoscapp.data

import com.rome.tech.horoscapp.domain.model.HoroscopeInfo
import com.rome.tech.horoscapp.domain.model.HoroscopeInfo.Aquarius
import com.rome.tech.horoscapp.domain.model.HoroscopeInfo.Aries
import com.rome.tech.horoscapp.domain.model.HoroscopeInfo.Cancer
import com.rome.tech.horoscapp.domain.model.HoroscopeInfo.Capricornus
import com.rome.tech.horoscapp.domain.model.HoroscopeInfo.Gemini
import com.rome.tech.horoscapp.domain.model.HoroscopeInfo.Leo
import com.rome.tech.horoscapp.domain.model.HoroscopeInfo.Libra
import com.rome.tech.horoscapp.domain.model.HoroscopeInfo.Pisces
import com.rome.tech.horoscapp.domain.model.HoroscopeInfo.Sagittarius
import com.rome.tech.horoscapp.domain.model.HoroscopeInfo.Scorpius
import com.rome.tech.horoscapp.domain.model.HoroscopeInfo.Taurus
import com.rome.tech.horoscapp.domain.model.HoroscopeInfo.Virgo
import javax.inject.Inject


class HoroscopeProvider  @Inject constructor() {
    fun getHoroscopes(): List<HoroscopeInfo> {
        return listOf(
            Aries,
            Taurus,
            Gemini,
            Cancer,
            Leo,
            Virgo,
            Libra,
            Scorpius,
            Sagittarius,
            Capricornus,
            Aquarius,
            Pisces
        )
    }


}