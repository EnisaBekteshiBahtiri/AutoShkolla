package com.example.autoshkolla.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.autoshkolla.databinding.LoginFragmentBinding
import com.example.autoshkolla.ui.register.RegisterFragmentDirections
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private lateinit var binding: LoginFragmentBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        if(firebaseAuth.currentUser != null){
            val directions = LoginFragmentDirections.actionLoginFragmentToNavHome()
            findNavController().navigate(directions)
        }
        onClick()
    }

    private fun onClick(){
        binding.registerBtn.setOnClickListener {
            val directions = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(directions)
        }

        binding.cirLoginButton.setOnClickListener{
            val psw = binding.editTextPassword.text.toString()
            val email = binding.editTextEmail.text.toString()

            if(email.isNotEmpty() && psw.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email,psw).addOnCompleteListener{
                    if(it.isSuccessful){
                        val directions = LoginFragmentDirections.actionLoginFragmentToNavHome()
                        findNavController().navigate(directions)
                    }else{
                        Toast.makeText(requireContext(),"Email or Password Incorrect!", Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(requireContext(),"Empty fields aren't allowed!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.forgotPswTxt.setOnClickListener{
            val directions = LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment()
            findNavController().navigate(directions)
        }

        binding.registerTxt.setOnClickListener{
            val directions = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(directions)
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