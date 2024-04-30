package com.olgunbingol.appointment_center.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.olgunbingol.appointment_center.R
import com.olgunbingol.appointment_center.adapter.randevularim_adapter
import com.olgunbingol.appointment_center.databinding.FragmentRandevularimBinding
import com.olgunbingol.appointment_center.model.Randevularim


class randevularimFragment : Fragment() {
    private lateinit var binding : FragmentRandevularimBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore
    private lateinit var randevularimArrayList : ArrayList<Randevularim>
    private lateinit var randevularimadapter : randevularim_adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_randevularim, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRandevularimBinding.bind(view)
        db = FirebaseFirestore.getInstance()
        randevularimArrayList = ArrayList<Randevularim>()
        getData()
        binding.randevuView.layoutManager = LinearLayoutManager(context)
        randevularimadapter = randevularim_adapter(randevularimArrayList)
        binding.randevuView.adapter = randevularimadapter


    }
    private fun getData() {
        db.collection("Randevularim").addSnapshotListener { value, error ->
            if (error!= null) {
                Toast.makeText(context,"Error!!!", Toast.LENGTH_LONG).show()

            }
            else {
                if(value != null) {
                    if(!value.isEmpty) {
                        val documents = value.documents
                        for(document in documents) {
                            val doktoradi = document.get("doktoradi") as String
                            val bolumadi = document.get("bolumadi") as String
                            val tarih = document.get("tarih") as String
                            val hastaneadi = document.get("hastaneadi") as String
                            val randevularim = Randevularim(doktoradi,bolumadi,hastaneadi,tarih)
                            randevularimArrayList.add(randevularim)

                        }
                       randevularimadapter.notifyDataSetChanged()

                    }
                }
            }
        }
    }


}