package com.example.autoshkolla.ui.home.categoryB.signs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.autoshkolla.adapters.categoryB.CategoryBSignsAdapter
import com.example.autoshkolla.api.ApiService
import com.example.autoshkolla.api.ServiceGenerator
import com.example.autoshkolla.databinding.CategoryBSignsFragmentBinding
import com.example.autoshkolla.model.SignModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Response

@AndroidEntryPoint
class CategoryBSigns : Fragment() {

    private lateinit var signsBDataAdapter: CategoryBSignsAdapter
    private lateinit var binding: CategoryBSignsFragmentBinding
    private var list: MutableList<SignModel>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CategoryBSignsFragmentBinding.inflate(inflater, container, false)
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
        signsBDataAdapter = CategoryBSignsAdapter()
        binding.categoryBSignsRecyclerView.apply {
            signsBDataAdapter.differ.submitList(list)
            adapter = signsBDataAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        signsBDataAdapter.setOnButtonClickListener {

        }
    }
}