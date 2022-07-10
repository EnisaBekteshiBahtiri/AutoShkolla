package com.example.autoshkolla.ui.home.categoryC.signs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.autoshkolla.adapters.categoryC.CategoryCSignsAdapter
import com.example.autoshkolla.api.ApiService
import com.example.autoshkolla.api.ServiceGenerator
import com.example.autoshkolla.databinding.CategoryCSignsFragmentBinding
import com.example.autoshkolla.model.SignModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Response

@AndroidEntryPoint
class CategoryCSigns : Fragment() {

    private lateinit var signsCDataAdapter: CategoryCSignsAdapter
    private lateinit var binding: CategoryCSignsFragmentBinding
    private var list: MutableList<SignModel>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CategoryCSignsFragmentBinding.inflate(inflater, container, false)
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
        signsCDataAdapter = CategoryCSignsAdapter()
        binding.categoryCSignsRecyclerView.apply {
            signsCDataAdapter.differ.submitList(list)
            adapter = signsCDataAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        signsCDataAdapter.setOnButtonClickListener {

        }
    }
}