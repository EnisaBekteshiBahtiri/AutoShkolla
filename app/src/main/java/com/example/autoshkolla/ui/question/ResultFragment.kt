package com.example.autoshkolla.ui.question

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.autoshkolla.databinding.ResultFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultFragment : Fragment() {
    private val viewModel by viewModels<ResultViewModel>()
    private lateinit var binding: ResultFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ResultFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args:ResultFragmentArgs by navArgs()

        val score=args.score
        binding.Score.text= score.toString()

          binding.button.setOnClickListener{
              val directions = ResultFragmentDirections.actionResultFragmentToNavHome()
              findNavController().navigate(directions)
          }
    }
}