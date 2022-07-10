package com.example.autoshkolla.ui.home.categoryD.signs

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.autoshkolla.R
import com.example.autoshkolla.adapters.categoryC.CategoryCSignsAdapter
import com.example.autoshkolla.adapters.categoryD.CategoryDSignsAdapter
import com.example.autoshkolla.api.ApiService
import com.example.autoshkolla.api.ServiceGenerator
import com.example.autoshkolla.databinding.CategoryCSignsFragmentBinding
import com.example.autoshkolla.databinding.CategoryDSignsFragmentBinding
import com.example.autoshkolla.model.SignModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Response

@AndroidEntryPoint
class CategoryDSigns : Fragment() {
    private lateinit var signsDDataAdapter: CategoryDSignsAdapter
    private lateinit var binding: CategoryDSignsFragmentBinding
    private var list: MutableList<SignModel>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CategoryDSignsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        val call = serviceGenerator.getCategoryBSigns()

        call.enqueue(object : retrofit2.Callback<MutableList<SignModel>>
        {
            override fun onResponse(
                call: Call<MutableList<SignModel>>,
                response: Response<MutableList<SignModel>>
            ) {
                if(response.isSuccessful){
                    list = response.body()!!
                    initAdapter()
                    Log.d("TAG", "onResponse: ${response.body()} ")
                }
            }

            override fun onFailure(call: Call<MutableList<SignModel>>, t: Throwable) {
                t.printStackTrace()
                Log.d("TAG", "onResponse: ERROR ")
            }
        })
    }


    private fun initAdapter() {
        signsDDataAdapter = CategoryDSignsAdapter()
        binding.categoryDSignsRecyclerView.apply {
            signsDDataAdapter.differ.submitList(list)
            adapter = signsDDataAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        signsDDataAdapter.setOnButtonClickListener {

        }
    }
}