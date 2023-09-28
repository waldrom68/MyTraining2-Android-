package com.rome.tech.horoscapp.ui.horoscope.adapter

import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.rome.tech.horoscapp.databinding.ItemHoroscopeBinding
import com.rome.tech.horoscapp.domain.model.HoroscopeInfo

class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemHoroscopeBinding.bind(view)

    fun bind(horoscopeInfo: HoroscopeInfo, onItemSelected: (HoroscopeInfo) -> Unit) {
        val context = binding.tvHoroscope.context
        binding.ivHroscope.setImageResource(horoscopeInfo.img)
        binding.tvHoroscope.text = context.getString(horoscopeInfo.name)

        binding.itemParent.setOnClickListener {
            starRotationImage(binding.ivHroscope, retainActionLambda = {
                // al pasar esta funcion lambda, la funcion la captura y la ejecuta cuando
                // se lo indique dentro de la animarion
                onItemSelected(horoscopeInfo)
            })


        }
    }

    private fun starRotationImage(view: View, retainActionLambda: () -> Unit) {
        view.animate().apply {
            duration = 300 // en milisegundos
            interpolator = LinearInterpolator() // el movimiento es a velocidad constante
            withEndAction(retainActionLambda)  // aqui le digo que ejecute el resto cuando termine la animacion
            rotationBy(360f)  // grados de la rotacion sobre su mismo eje
            start()
        }
    }

}