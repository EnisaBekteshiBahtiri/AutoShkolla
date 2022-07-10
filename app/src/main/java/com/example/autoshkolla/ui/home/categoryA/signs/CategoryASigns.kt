package com.example.autoshkolla.ui.home.categoryA.signs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.autoshkolla.adapters.categoryA.CategoryASignsAdapter
import com.example.autoshkolla.api.ApiService
import com.example.autoshkolla.api.ServiceGenerator
import com.example.autoshkolla.databinding.FragmentCategoryASignsBinding
import com.example.autoshkolla.model.SignModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Response

@AndroidEntryPoint
class CategoryASigns : Fragment() {

    private val viewModel by viewModels<CategoryASignsViewModel>()
    private lateinit var signsDataAdapter: CategoryASignsAdapter
    private lateinit var binding: FragmentCategoryASignsBinding
    private var list: MutableList<SignModel>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryASignsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        val call = serviceGenerator.getCategoryASigns()

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

        signsDataAdapter = CategoryASignsAdapter()
        binding.categoryASignsRecyclerView.apply {
            val newList = list!!.filter { it.id <=6 }
            Log.d("TAG", "Lista e re: $newList")
            signsDataAdapter.differ.submitList(newList)
            adapter = signsDataAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        signsDataAdapter.setOnButtonClickListener {

        }
    }
}