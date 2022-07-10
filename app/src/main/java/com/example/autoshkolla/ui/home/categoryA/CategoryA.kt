package com.example.autoshkolla.ui.home.categoryA

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.autoshkolla.adapters.categoryA.CategoryAAdapter
import com.example.autoshkolla.api.ApiService
import com.example.autoshkolla.model.CategoryModel
import com.example.autoshkolla.api.ServiceGenerator
import com.example.autoshkolla.databinding.CategoryAFragmentBinding
import retrofit2.Call
import retrofit2.Response

class CategoryA : Fragment() {
    private lateinit var binding: CategoryAFragmentBinding
    lateinit var myAdapter: CategoryAAdapter
    private var list: MutableList<CategoryModel>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CategoryAFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        val call = serviceGenerator.getCategoryAData()

        call.enqueue(object : retrofit2.Callback<MutableList<CategoryModel>>
            {
            override fun onResponse(
                call: Call<MutableList<CategoryModel>>,
                response: Response<MutableList<CategoryModel>>
            ) {
               if(response.isSuccessful){
                   list = response.body()!!
                   initAdapter()

                   Log.d("TAG", "onResponse: ${response.body()} ")
//                   val responseBody = response.body()!!
               }
            }

            override fun onFailure(call: Call<MutableList<CategoryModel>>, t: Throwable) {
               t.printStackTrace()
                Log.d("TAG", "onResponse: ERROR ")
            }


        }
        )
    }

    private fun initAdapter(){
        myAdapter = CategoryAAdapter()
        binding.categoryARv.apply {
            myAdapter.differ.submitList(list?.filter { it.categoryId == 1 })
            adapter = myAdapter
            layoutManager = LinearLayoutManager(requireContext())

        }
        myAdapter.setOnItemClickListener {
            if (it.id == 1){
                val directions = CategoryADirections.actionCategoryAToCategoryARules()
                findNavController().navigate(directions)
            }
            if (it.id == 2){
                val directions = CategoryADirections.actionCategoryAToCategoryASigns()
                findNavController().navigate(directions)
            }
            if (it.id == 3){
                val directions = CategoryADirections.actionCategoryAToQuestionFragment()
                findNavController().navigate(directions)
            }
        }
    }
}

