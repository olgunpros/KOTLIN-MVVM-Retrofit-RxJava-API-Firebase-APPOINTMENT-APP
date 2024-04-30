package com.olgunbingol.appointment_center.view

import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.olgunbingol.appointment_center.R

class startFragment : Fragment() {
    private lateinit var loginClicked: Button
    private lateinit var mailText: TextView
    private lateinit var passwordText: TextView
    private lateinit var hatirla: Switch
    private lateinit var auth: FirebaseAuth
    private lateinit var signinClicked: Button
    private lateinit var resetClicked : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        hatirla = view.findViewById(R.id.hatirla)
        mailText = view.findViewById(R.id.mailText)
        passwordText = view.findViewById(R.id.passwordText)


        // SharedPreferences'ten switch'in durumunu al
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val hatirlaChecked = preferences.getBoolean("hatirla", false)
        hatirla.isChecked = hatirlaChecked

        // Eğer hatırla açıksa ve kullanıcı daha önce giriş yaptıysa direkt olarak homeFragment'e git
        if (hatirla.isChecked && auth.currentUser != null) {
            findNavController().navigate(R.id.action_startFragment_to_homeFragment)
        }

        loginClicked = view.findViewById(R.id.loginClicked)
        loginClicked.setOnClickListener {
            val mail = mailText.text.toString()
            val password = passwordText.text.toString()
            if(mail.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(mail, password).addOnSuccessListener {
                    // Kullanıcı giriş yaptıktan sonra switch'in durumunu kaydet
                    preferences.edit().putBoolean("hatirla", hatirla.isChecked).apply()
                    findNavController().navigate(R.id.action_startFragment_to_homeFragment)
                }.addOnFailureListener {
                    Toast.makeText(context,it.localizedMessage,Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(context, "Email ? / Password ? ", Toast.LENGTH_SHORT).show()
            }
        }

        // Switch'in durumu değiştiğinde kullanıcıya göre davranışı belirle
        hatirla.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked && auth.currentUser != null) {
                // Switch açıksa ve kullanıcı giriş yaptıysa direkt olarak homeFragment'e git
                findNavController().navigate(R.id.action_startFragment_to_homeFragment)
            } else {
             Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show()

            }
        }

        signinClicked = view.findViewById(R.id.kayitetClicked)
        signinClicked.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_signinFragment)
        }
        resetClicked = view.findViewById(R.id.resetClicked)
        resetClicked.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_resetPasswordFragment)
        }
    }
}
