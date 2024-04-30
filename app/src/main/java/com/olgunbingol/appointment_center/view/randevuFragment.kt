package com.olgunbingol.appointment_center.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.olgunbingol.appointment_center.R
import com.olgunbingol.appointment_center.adapter.recycler_adapter
import com.olgunbingol.appointment_center.databinding.FragmentRandevuBinding
import com.olgunbingol.appointment_center.model.Post


class randevuFragment : Fragment() {
    private lateinit var randevualsinClicked : Button
    private lateinit var db: FirebaseFirestore
    private lateinit var storage : FirebaseStorage
    private lateinit var database : FirebaseDatabase
    private lateinit var postArrayList : ArrayList<Post>
    private lateinit var randevuadapter : recycler_adapter
    private lateinit var binding: FragmentRandevuBinding








    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_randevu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRandevuBinding.bind(view)
        db = FirebaseFirestore.getInstance()
        postArrayList = ArrayList<Post>()
        getData()
        binding.randevurecyclerView.layoutManager = LinearLayoutManager(context)
        randevuadapter = recycler_adapter(postArrayList)
        binding.randevurecyclerView.adapter = randevuadapter






    }
    private fun getData() {
        db.collection("Posts").addSnapshotListener { value, error ->
            if (error!= null) {
                Toast.makeText(context,"Error!!!",Toast.LENGTH_LONG).show()

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
                            val post = Post(doktoradi,bolumadi,hastaneadi,tarih)
                            postArrayList.add(post)

                        }
                        randevuadapter.notifyDataSetChanged()

                    }
                }
            }
        }
    }




}