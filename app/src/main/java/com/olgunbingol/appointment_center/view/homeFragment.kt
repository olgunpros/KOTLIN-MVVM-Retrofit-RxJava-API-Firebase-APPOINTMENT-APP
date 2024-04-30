package com.olgunbingol.appointment_center.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.olgunbingol.appointment_center.R


class homeFragment : Fragment() {
    private lateinit var menuClicked : Button
    private lateinit var randevualClicked : Button
    private lateinit var randevuClicked : Button
    private lateinit var talepClicked : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        randevuClicked = view.findViewById(R.id.randevuClicked)
        randevuClicked.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_randevularimFragment)
        }
        randevualClicked = view.findViewById(R.id.randevualClicked)
        randevualClicked.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_randevuFragment)
        }

            menuClicked = view.findViewById(R.id.menuClicked)
        menuClicked.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_menuFragment)
        }
        talepClicked = view.findViewById(R.id.talepClicked)
        talepClicked.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_taleplerimFragment)
        }


    }


}