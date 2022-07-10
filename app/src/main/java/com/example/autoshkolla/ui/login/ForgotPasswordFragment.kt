package com.example.autoshkolla.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.autoshkolla.databinding.ForgotPasswordFragmentBinding
import com.example.autoshkolla.databinding.FragmentHomeBinding
import com.example.autoshkolla.ui.home.HomeFragmentDirections
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordFragment : Fragment() {

    private var _binding: ForgotPasswordFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ForgotPasswordFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goBackBtn.setOnClickListener {
            val directions = ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToLoginFragment()
            findNavController().navigate(directions)
        }

        binding.submitBtn.setOnClickListener{
            val email: String = binding.editTextEmail.text.toString().trim{ it<= ' '}
            if (email.isEmpty()){
                Toast.makeText(requireContext(),"Please enter email address.",Toast.LENGTH_SHORT).show()
            }else{
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener{
                        task ->
                        if(task.isSuccessful){
                            Toast.makeText(requireContext(),"Email sent successfully to reset your password!",Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(requireContext(),task.exception!!.message.toString(),Toast.LENGTH_LONG).show()
                        }
                    }
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