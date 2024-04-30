package com.olgunbingol.appointment_center.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olgunbingol.appointment_center.databinding.FragmentRandevularimBinding
import com.olgunbingol.appointment_center.databinding.RandevuRowBinding
import com.olgunbingol.appointment_center.databinding.RecyclerRowBinding
import com.olgunbingol.appointment_center.model.Randevularim

class randevularim_adapter( val randevularimList : ArrayList<Randevularim>) :RecyclerView.Adapter<randevularim_adapter.RandevularimHolder>() {
    class RandevularimHolder(val binding : RandevuRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandevularimHolder {
val binding = RandevuRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RandevularimHolder(binding)
    }
    override fun getItemCount(): Int {
return randevularimList.size    }

    override fun onBindViewHolder(holder: RandevularimHolder, position: Int) {
        holder.binding.listeledoktorText.text = randevularimList.get(position).doktoradi
        holder.binding.listelehastaneText.text = randevularimList.get(position).hastaneadi
        holder.binding.listelebolumText.text = randevularimList.get(position).bolumadi
        holder.binding.listeletarihText.text = randevularimList.get(position).tarih    }
}