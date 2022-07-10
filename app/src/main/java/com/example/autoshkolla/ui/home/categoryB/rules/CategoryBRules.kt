package com.example.autoshkolla.ui.home.categoryB.rules

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.autoshkolla.adapters.categoryB.CategoryBRulesAdapter
import com.example.autoshkolla.databinding.CategoryBRulesFragmentBinding
import com.example.autoshkolla.model.RuleModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryBRules : Fragment() {

    private val viewModel  by viewModels<CategoryBRulesViewModel>()

    private lateinit var rulesBDataAdapter: CategoryBRulesAdapter
    private lateinit var binding: CategoryBRulesFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CategoryBRulesFragmentBinding.inflate(inflater, container, false)
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
                val newList = it.filter { it -> it.id in 5..15 }
                rulesBDataAdapter.differ.submitList(newList)
                Log.d(ContentValues.TAG, "DATAAAAA: $it")
            }
        }

        viewModel.deleteRuleFromDB.observe(viewLifecycleOwner){
            //   viewModel.deleteRuleFromDB(2)
        }

    }

    private fun insertData() {
        viewModel.insertRuleToDB(
            RuleModel(5,"General Rules Keep Left\n","on a two-way road to allow traffic from the opposite direction to pass on your right \n and on a one-way road to allow vehicles behind you to overtake from your right. ")
        )
        viewModel.insertRuleToDB(
            RuleModel(6,"When Turning Left","keep to the left side of the road you are leaving as well as the one you are entering. " +
                    " When turning right, move to the centre of the road you" +
                    " are leaving and arrive near the left side of road you are entering.")
        )
        viewModel.insertRuleToDB(RuleModel(7,"Slow Down","at road junctions, intersections, pedestrian crossings and road corners  and wait until you are sure of a clear passage ahead. if you are entering a main road where traffic is not being regulated, give way to vehicles passing on your right."))
        viewModel.insertRuleToDB(
            RuleModel(8,"Hand Signals","are necessary at certain times. When slowing down, extend your right arm palm down and swing it up and down; when stopping, raise your forearm vertically outside the vehicle; when turning right or changing lane to the right hand side, extend your right arm straight out, palm to the front; when turning left or changing lane to the left hand side, extend your right arm and rotate it in an anti-clockwise direction.")
        )
        viewModel.insertRuleToDB(
            RuleModel(9,"Direction Indicators","Better use directions indicators instead of hands singlals and both in case of any emergancy.")
        )
        viewModel.insertRuleToDB(
            RuleModel(10,"Wearing a Helmet for Two Wheeler Drivers","is a statutory requirement. The helmet must conform to the ISI standards and should bear the ISI mark. Helmet works as a shield for your head in case of a mishap. It is designed for your individual safety and not as a cover to avoid legal prosecution. For complete safety tie the strap properly otherwise the helmet may slip from your head in case of an accident head injury. (Turban wearing Sikhs are exempted from using a helmet).")
        )
        viewModel.insertRuleToDB(
            RuleModel(11,"Do Not Park","at or near a road crossing or on top of a hill or on a footpath; too near a traffic light or pedestrian crossing; on a main road or a road with heavy traffic; in front of or opposite another parked vehicle to cause obstruction; on roads that have a white line; near a bus- stop, school or hospital entrance; right next to a traffic sign thereby blocking it for others; at the entrance of a building; near a fire hydrant thereby blocking access to it; where parking is specifically prohibited.")
        )
        viewModel.insertRuleToDB(
            RuleModel(12,"The Registration Mark","of the vehicle should be clear, legible and visible at all times. Do not load the motor vehicle so as to obstruct the tail lights or any other lights or marks required on the vehicle for its safety.")
        )
        viewModel.insertRuleToDB(
            RuleModel(13,"Do Not Drive","on a one way road except in the direction permitted. Reversing into a one way street in the wrong direction, is also prohibited.")
        )
        viewModel.insertRuleToDB(
            RuleModel(14,"Do Not Cross The Yellow Line","dividing the road even while overtaking. On roads with defined lanes use appropriate indicator signal before changing lanes.")
        )
        viewModel.insertRuleToDB(
            RuleModel(15,"Do Not Cross The Stop Line","painted on the road when you stop at a road junction or intersection or a pedestrian crossing. In no case should your stationary vehicle project,beyond this line.")
        )

    }

    private fun initAdapter() {
        rulesBDataAdapter = CategoryBRulesAdapter()
        binding.categoryBRulesRecyclerView.apply {
            adapter = rulesBDataAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        rulesBDataAdapter.setOnButtonClickListener {

        }
    }

    private fun getData() {
        viewModel.getRulesFromDB()
    }
}