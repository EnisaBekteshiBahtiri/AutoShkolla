package com.example.autoshkolla.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.autoshkolla.databinding.FragmentHomeBinding
import com.example.autoshkolla.ui.login.LoginFragmentDirections
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var user: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
    }

    private fun onClick(){

        binding.test.setOnClickListener{
            val directions = HomeFragmentDirections.actionNavHomeToQuestionFragment()
            findNavController().navigate(directions)

        }

        binding.motorcycleCardView.setOnClickListener{
            val directions = HomeFragmentDirections.actionNavHomeToCategoryA()
            findNavController().navigate(directions)
        }

        binding.carCardView.setOnClickListener{
            val directions = HomeFragmentDirections.actionNavHomeToCategoryB()
            findNavController().navigate(directions)
        }

        binding.truckCardView.setOnClickListener{
            val directions = HomeFragmentDirections.actionNavHomeToCategoryC()
            findNavController().navigate(directions)
        }

        binding.busCardView.setOnClickListener{
            val directions = HomeFragmentDirections.actionNavHomeToCategoryD()
            findNavController().navigate(directions)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}