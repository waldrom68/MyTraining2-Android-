package com.rome.tech.horoscapp.ui.horoscope.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rome.tech.horoscapp.R
import com.rome.tech.horoscapp.domain.model.HoroscopeInfo

class HoroscopeAdapter(
    private var horoscopeList: List<HoroscopeInfo> = emptyList(),
    private var onItemSelected: (HoroscopeInfo) -> Unit,
) :
    RecyclerView.Adapter<HoroscopeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HoroscopeViewHolder(layoutInflater.inflate(R.layout.item_horoscope, parent, false))
    }

    override fun getItemCount() = horoscopeList.size

    override fun onBindViewHolder(viewholder: HoroscopeViewHolder, position: Int) {
        viewholder.bind(horoscopeList[position], onItemSelected )
    }

    fun updateList(horoscopeList: List<HoroscopeInfo>) {
        this.horoscopeList = horoscopeList
        notifyDataSetChanged()
    }

}