package com.olgunbingol.appointment_center.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import com.olgunbingol.appointment_center.adapter.taleplerim_adapter
import com.olgunbingol.appointment_center.databinding.FragmentRandevuBinding
import com.olgunbingol.appointment_center.databinding.FragmentTaleplerimBinding
import com.olgunbingol.appointment_center.model.Randevularim
import com.olgunbingol.appointment_center.model.Taleplerim


class taleplerimFragment : Fragment() {
    private lateinit var talepText : TextView
    private lateinit var olusturClicked : Button
    private lateinit var db: FirebaseFirestore
    private lateinit var storage : FirebaseStorage
    private lateinit var database : FirebaseDatabase
    private lateinit var binding : FragmentTaleplerimBinding
    private lateinit var taleplerimArrayList : ArrayList<Taleplerim>
    private lateinit var taleplerimAdapter : taleplerim_adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTaleplerimBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = FirebaseFirestore.getInstance()
        storage = FirebaseStorage.getInstance()
        taleplerimArrayList = ArrayList<Taleplerim>()
        binding.talepView.layoutManager = LinearLayoutManager(context)
        database = FirebaseDatabase.getInstance()
        talepText = binding.talepText
        olusturClicked = binding.olusturCLicked
        taleplerimAdapter = taleplerim_adapter(taleplerimArrayList)
        binding.talepView.adapter = taleplerimAdapter

        getData()

        olusturClicked.setOnClickListener {
            getData()
         // upload()

        }
    }

    fun upload() {
        val randevuMap = hashMapOf<String,Any>()
        randevuMap.put("talepadi", binding.talepText.text.toString())
        db.collection("Taleplerim").add(randevuMap).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(context, "Successful", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, task.exception?.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun getData() {
        db.collection("Taleplerim").addSnapshotListener { value, error ->
            if (error != null) {
                Toast.makeText(context, "Error!!!", Toast.LENGTH_LONG).show()
            } else {
                if (value != null && !value.isEmpty) {
                    val documents = value.documents
                    taleplerimArrayList.clear()
                    for (document in documents) {
                        val talepadi = document.getString("talepadi") as String
                        val taleplerim = Taleplerim(talepadi)
                        taleplerimArrayList.add(taleplerim)
                    }
                    taleplerimAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}
