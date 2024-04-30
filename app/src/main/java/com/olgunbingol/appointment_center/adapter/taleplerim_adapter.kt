package com.olgunbingol.appointment_center.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olgunbingol.appointment_center.databinding.RecyclerRowBinding
import com.olgunbingol.appointment_center.databinding.FragmentTaleplerimBinding
import com.olgunbingol.appointment_center.databinding.TalepRowBinding
import com.olgunbingol.appointment_center.model.Taleplerim

class taleplerim_adapter(val taleplerimList : ArrayList<Taleplerim>): RecyclerView.Adapter<taleplerim_adapter.TaleplerimHolder>()  {
    class TaleplerimHolder(val binding: TalepRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaleplerimHolder {
        val binding = TalepRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaleplerimHolder(binding)
    }

    override fun getItemCount(): Int {
        return taleplerimList.size
    }

    override fun onBindViewHolder(holder: TaleplerimHolder, position: Int) {
       holder.binding.talepsonucText.text = taleplerimList.get(position).talepadi

    }
}
