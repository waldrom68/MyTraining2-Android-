package com.rome.tech.horoscapp.ui.horoscope.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rome.tech.horoscapp.databinding.ItemHoroscopeBinding
import com.rome.tech.horoscapp.domain.model.HoroscopeInfo

class HoroscopeViewHolder(view:View): RecyclerView.ViewHolder(view) {

    private val binding = ItemHoroscopeBinding.bind(view)

    fun bind(horoscopeInfo: HoroscopeInfo) {
        val context = binding.tvHoroscope.context

        binding.ivHroscope.setImageResource(horoscopeInfo.img)
        binding.tvHoroscope.text = context.getString(horoscopeInfo.name)
    }
}