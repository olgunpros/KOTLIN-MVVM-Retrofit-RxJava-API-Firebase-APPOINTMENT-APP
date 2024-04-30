package com.olgunbingol.appointment_center.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olgunbingol.appointment_center.databinding.RecyclerRowBinding
import com.olgunbingol.appointment_center.databinding.TalepRowBinding
import com.olgunbingol.appointment_center.model.Post

class recycler_adapter(val postList : ArrayList<Post>) : RecyclerView.Adapter<recycler_adapter.PostHolder>(){
    class PostHolder(val binding : RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
return PostHolder(binding)
    }

    override fun getItemCount(): Int {
return postList.size    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {



        holder.binding.randevudoktorText.text = postList.get(position).doktoradi
        holder.binding.randevuhastaneText.text = postList.get(position).hastaneadi
        holder.binding.randevubolumText.text = postList.get(position).bolumadi
        holder.binding.randevutarihText.text = postList.get(position).tarih


    }


}