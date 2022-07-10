package com.example.autoshkolla.ui.rate_us

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.autoshkolla.R
import com.example.autoshkolla.databinding.FragmentRateUsBinding

class RateUsFragment : Fragment() {

    private var _binding: FragmentRateUsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRateUsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->

            if (rating <= 1) {
                binding.img.setImageResource(R.drawable.rate_1)
            } else if (rating <= 2) {
                binding.img.setImageResource(R.drawable.rate_2)
            } else if (rating <= 3) {
                binding.img.setImageResource(R.drawable.rate_3)
            } else if (rating <= 4) {
                binding.img.setImageResource(R.drawable.rate_4)
            } else if (rating <= 5) {
                binding.img.setImageResource(R.drawable.rate_5)
            }
            binding.ratingValue.text = rating.toString()
        }
    }
}