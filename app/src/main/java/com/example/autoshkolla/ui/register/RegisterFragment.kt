package com.example.autoshkolla.ui.register

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.autoshkolla.R
import com.example.autoshkolla.databinding.LoginFragmentBinding
import com.example.autoshkolla.databinding.RegisterFragmentBinding
import com.example.autoshkolla.ui.login.LoginFragmentDirections
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment : Fragment() {

    private lateinit var binding: RegisterFragmentBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RegisterFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()

        firebaseAuth = FirebaseAuth.getInstance()

    }

    private fun onClick(){
        binding.loginBtn.setOnClickListener {
            val directions = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            findNavController().navigate(directions)
        }

        binding.cirRegisterButton.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val email = binding.editTextEmail.text.toString()
            val phoneNumber = binding.editTextMobile.text.toString()
            val psw = binding.editTextPassword.text.toString()

            if(email.isNotEmpty() && name.isNotEmpty() && phoneNumber.isNotEmpty() && psw.isNotEmpty()){
                firebaseAuth.createUserWithEmailAndPassword(email,psw).addOnCompleteListener{
                    if(it.isSuccessful){
                        val directions = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                        findNavController().navigate(directions)
                    }else{
                        Toast.makeText(requireContext(),it.exception.toString(),Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(requireContext(),"Empty fields aren't allowed!",Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }

}