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


class resetPasswordFragment : Fragment() {
    private lateinit var sifirlaText : TextView
    private lateinit var sendClicked : Button
    private lateinit var auth : FirebaseAuth
    private lateinit var againemailText : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reset_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        sifirlaText = view.findViewById(R.id.sifirlaText)
        sendClicked = view.findViewById(R.id.sendClicked)
        againemailText = view.findViewById(R.id.againemailText)

        sendClicked.setOnClickListener {

            val email = sifirlaText.text.toString() // email değerini burada alın
            val email2 = againemailText.text.toString()
            if (email.isNotEmpty()&& email2.isNotEmpty()) {
                if(email == email2) {
                    auth.sendPasswordResetEmail(email).addOnCompleteListener {
                        findNavController().navigate(R.id.action_resetPasswordFragment_to_startFragment)
                        Toast.makeText(context, "Email sending...", Toast.LENGTH_LONG).show()
                    }.addOnFailureListener {
                        Toast.makeText(context, it.localizedMessage, Toast.LENGTH_LONG).show()
                    }
                }
                else {
                    Toast.makeText(context, "Email not match, please try again.", Toast.LENGTH_LONG).show()

                }
                }
                 else { // email boşsa kullanıcıya uyarı verin
                Toast.makeText(context, "Please enter your email", Toast.LENGTH_LONG).show()
            }

        }
    }




    }


