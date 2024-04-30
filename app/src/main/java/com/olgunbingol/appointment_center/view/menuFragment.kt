package com.olgunbingol.appointment_center.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.olgunbingol.appointment_center.R


class menuFragment : Fragment() {
    private lateinit var cikisClicked : Button
    private lateinit var auth : FirebaseAuth
    private lateinit var nobetciiClicked : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nobetciiClicked = view.findViewById(R.id.nobetciiClicked)
        nobetciiClicked.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_nobetcieczaneFragment)
        }
        auth = FirebaseAuth.getInstance()
        cikisClicked = view.findViewById(R.id.cikisClicked)
        cikisClicked.setOnClickListener {
            auth.signOut()
            findNavController().navigate(R.id.action_menuFragment_to_startFragment)
            Toast.makeText(context,"Succesfull!",Toast.LENGTH_LONG).show()

        }


    }


}