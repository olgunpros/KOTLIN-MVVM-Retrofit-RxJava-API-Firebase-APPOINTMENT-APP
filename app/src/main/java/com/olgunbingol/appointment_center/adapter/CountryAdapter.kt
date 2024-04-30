package com.olgunbingol.appointment_center.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.olgunbingol.appointment_center.R
import com.olgunbingol.appointment_center.databinding.FragmentNobetcieczaneBinding
import com.olgunbingol.appointment_center.databinding.FragmentTaleplerimBinding
import com.olgunbingol.appointment_center.databinding.ItemCountryBinding
import com.olgunbingol.appointment_center.model.Country


class CountryAdapter (val countryList : ArrayList<Country>) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(){
class CountryViewHolder(var binding : ItemCountryBinding) : RecyclerView.ViewHolder(binding.root){
    val eczaneAdi: TextView = itemView.findViewById(R.id.eczaneAdi)





}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {

val inflater = LayoutInflater.from(parent.context)
    val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    return CountryViewHolder(binding)
    }

    override fun getItemCount(): Int {
return countryList.size    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {


        holder.binding.eczaneAdi.text = countryList[position].pharmacyName
        holder.binding.eczaneIlce.text = countryList[position].district
        holder.binding.eczaneAdres.text = countryList[position].address


    }
    fun updateCountryList(newCountryList : List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()

    }
    fun getlongitude(position: Int): Pair<Double?, Double?> {
        val latitude = countryList[position].latitude
        val longitude = countryList[position].longitude
        return Pair(latitude,longitude)

    }
}