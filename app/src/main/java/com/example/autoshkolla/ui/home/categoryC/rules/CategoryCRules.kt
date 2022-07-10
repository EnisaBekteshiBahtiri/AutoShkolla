package com.example.autoshkolla.ui.home.categoryC.rules

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.autoshkolla.R
import com.example.autoshkolla.adapters.categoryB.CategoryBRulesAdapter
import com.example.autoshkolla.adapters.categoryC.CategoryCRulesAdapter
import com.example.autoshkolla.databinding.CategoryBRulesFragmentBinding
import com.example.autoshkolla.databinding.CategoryCRulesFragmentBinding
import com.example.autoshkolla.model.RuleModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryCRules : Fragment() {

    private val viewModel  by viewModels<CategoryCRulesViewModel>()

    private lateinit var rulesCDataAdapter: CategoryCRulesAdapter
    private lateinit var binding: CategoryCRulesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CategoryCRulesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        insertData()
        getData()
        initAdapter()
        observeData()

    }

    private fun observeData(){
        viewModel.getRulesFromDB.observe(viewLifecycleOwner) {
            insertData()
            if(!it.isNullOrEmpty()){
                val newList = it.filter { it -> it.id in 16..20 }
                rulesCDataAdapter.differ.submitList(newList)
                Log.d(ContentValues.TAG, "DATAAAAA: $it")
            }
        }

        viewModel.deleteRuleFromDB.observe(viewLifecycleOwner){
            //   viewModel.deleteRuleFromDB(2)
        }

    }

    private fun insertData() {
        viewModel.insertRuleToDB(
            RuleModel(16,"General Rules Keep Left\n","on a two-way road to allow traffic from the opposite direction to pass on your right \n and on a one-way road to allow vehicles behind you to overtake from your right. ")
        )
        viewModel.insertRuleToDB(
            RuleModel(17,"When Turning Left","keep to the left side of the road you are leaving as well as the one you are entering. " +
                    " When turning right, move to the centre of the road you" +
                    " are leaving and arrive near the left side of road you are entering.")
        )
        viewModel.insertRuleToDB(RuleModel(18,"Slow Down","at road junctions, intersections, pedestrian crossings and road corners  and wait until you are sure of a clear passage ahead. if you are entering a main road where traffic is not being regulated, give way to vehicles passing on your right."))
        viewModel.insertRuleToDB(
            RuleModel(19,"Hand Signals","are necessary at certain times. When slowing down, extend your right arm palm down and swing it up and down; when stopping, raise your forearm vertically outside the vehicle; when turning right or changing lane to the right hand side, extend your right arm straight out, palm to the front; when turning left or changing lane to the left hand side, extend your right arm and rotate it in an anti-clockwise direction.")
        )
        viewModel.insertRuleToDB(
            RuleModel(20,"Direction Indicators","Better use directions indicators instead of hands singlals and both in case of any emergancy.")
        )
    }

    private fun initAdapter() {
        rulesCDataAdapter = CategoryCRulesAdapter()
        binding.categoryCRulesRecyclerView.apply {
            adapter = rulesCDataAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        rulesCDataAdapter.setOnButtonClickListener {

        }
    }

    private fun getData() {
        viewModel.getRulesFromDB()
    }
}