package com.example.autoshkolla.ui.home.categoryA.rules

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.autoshkolla.adapters.categoryA.CategoryARulesAdapter
import com.example.autoshkolla.databinding.FragmentCategoryARulesBinding
import com.example.autoshkolla.model.RuleModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryARules : Fragment() {
    private val viewModel by viewModels<CategoryARulesViewModel>()
    private lateinit var rulesDataAdapter: CategoryARulesAdapter
    private lateinit var binding: FragmentCategoryARulesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryARulesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        initAdapter()
        observeData()

    }

    private fun observeData(){
        viewModel.getRulesFromDB.observe(viewLifecycleOwner) {
            if(it.isNullOrEmpty()){
                insertData()
            }
            if(!it.isNullOrEmpty()){
             var newList = it.filter { it -> it.id <= 4 }
            rulesDataAdapter.differ.submitList(newList)
            Log.d(TAG, "DATAAAAA: $it")
        }
        }

        viewModel.deleteRuleFromDB.observe(viewLifecycleOwner){
            //   viewModel.deleteRuleFromDB(2)
        }

    }

    private fun insertData() {
        viewModel.insertRuleToDB(
            RuleModel(1,"Lane filtering rules\n","Lane filtering is when you ride your motorcycle at low speed between stationary or slow moving vehicles travelling in the same direction you are. The 2 lines or lanes of traffic must both be travelling in the same direction. If one of the lines or lanes is a dedicated turning lane (left or right) you must not lane filter using this lane.\n " +
                    "You're only allowed to lane filter in Queensland if you hold an open licence for the motorcycle you are riding and:\nyour speed when filtering is 30km/h or less\nit's safe to do so. ")
        )
        viewModel.insertRuleToDB(RuleModel(2,"Rules for riding on road shoulders","If you hold an open licence for the motorcycle you are riding, " +
                "you can ride on road shoulders and in emergency stopping lanes on major roads—such as highways," +
                " freeways and motorways past stationary or slow-moving traffic if:\n the speed limit is 90km/h or more\nyour speed is 30km/h or less\nyou give way to bicycle riders or other motorcycle riders already using the shoulder\nyou are not riding on any unsealed parts of the road" +
                "\nthere are no roadworks\nyou are not in a tunnel\nit is safe to do so."))
        viewModel.insertRuleToDB(RuleModel(3,"Motorcycle control rules","You must stay in control of your motorcycle at all times. You must sit with 1 leg on either side of your seat at all times when riding a motorcycle, but you can lift your leg from the footrests or raise yourself from the seat if you need to while riding."))
        viewModel.insertRuleToDB(RuleModel(4,"Rules for carrying passengers on any motorcycle","All of your passengers must wear an approved motorcycle helmet securely fastened." +
                "\nPillion passengers must be at least 8 years old and their feet must be able to reach the passenger footrests while seated.\n" +
                "You must not carry more passengers on the motorcycle or in the motorcycle's sidecar than the vehicle was designed to carry.\n" +
                "Your pillion passenger must not ride on the motorcycle unless the motorcycle has a suitable pillion seat and suitable passenger footrests.\n" +
                "Your passenger must not interfere with your control of the motorcycle."))
    }


    private fun getData() {
        viewModel.getRulesFromDB()

    }
    private fun initAdapter() {
        rulesDataAdapter = CategoryARulesAdapter()
        binding.categoryARulesRecyclerView.apply {
            adapter = rulesDataAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        rulesDataAdapter.setOnButtonClickListener {

        }
    }
}