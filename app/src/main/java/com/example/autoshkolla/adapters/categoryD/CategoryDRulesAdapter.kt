package com.example.autoshkolla.adapters.categoryD

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.autoshkolla.databinding.ItemDRulesBinding
import com.example.autoshkolla.model.RuleModel

class CategoryDRulesAdapter : RecyclerView.Adapter<CategoryDRulesAdapter.UserDataViewHolder>() {

    private val  differCallBack = object : DiffUtil.ItemCallback<RuleModel>(){
        override fun areItemsTheSame(oldItem: RuleModel, newItem: RuleModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RuleModel, newItem: RuleModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDataViewHolder {
        val binding = ItemDRulesBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return UserDataViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    override fun onBindViewHolder(holder: UserDataViewHolder, position: Int) {
        val user = differ.currentList[position]
        holder.bind(user)
    }

    inner class UserDataViewHolder(
        private val binding: ItemDRulesBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(ruleModel: RuleModel) {

            binding.ruleDId.text = ruleModel.id.toString()
            binding.ruleDTitle.text = ruleModel.title
            binding.ruleDDescription.text = ruleModel.description

        }
    }

    private var onButtonClickListener :((RuleModel)->Unit)?=null

    fun setOnButtonClickListener(listener : (RuleModel)->Unit){
        onButtonClickListener = listener
    }


}