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
import com.google.firebase.auth.FirebaseAuth
import com.olgunbingol.appointment_center.R

class signinFragment : Fragment() {
    private lateinit var emailekleText : TextView
    private lateinit var passwordekleText : TextView
    private lateinit var passwordyenidenText : TextView
    private lateinit var kayitClicked : Button
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        emailekleText = view.findViewById(R.id.emailekleText)
        passwordekleText = view.findViewById(R.id.passwordekleText)
        passwordyenidenText = view.findViewById(R.id.passwordyenidenText)
        kayitClicked = view.findViewById(R.id.kayitClicked)

        kayitClicked.setOnClickListener {
            val email = emailekleText.text.toString()
            val password = passwordekleText.text.toString()
            val password2 = passwordyenidenText.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty() && password2.isNotEmpty()) {
                if(password == password2) {
                    auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                        findNavController().navigate(R.id.action_signinFragment_to_homeFragment)
                    }.addOnFailureListener { exception ->
                        Toast.makeText(context, exception.localizedMessage, Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(context, "Passwords do not match!", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(context, "Email and password fields cannot be empty!", Toast.LENGTH_LONG).show()
            }
        }
    }
}
