package com.example.autoshkolla.adapters.categoryC

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.autoshkolla.databinding.ItemCRulesBinding
import com.example.autoshkolla.model.RuleModel

class CategoryCRulesAdapter : RecyclerView.Adapter<CategoryCRulesAdapter.UserDataViewHolder>() {

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
        val binding = ItemCRulesBinding
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
        private val binding: ItemCRulesBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(ruleModel: RuleModel) {

            binding.ruleCId.text = ruleModel.id.toString()
            binding.ruleCTitle.text = ruleModel.title
            binding.ruleCDescription.text = ruleModel.description

        }
    }

    private var onButtonClickListener :((RuleModel)->Unit)?=null

    fun setOnButtonClickListener(listener : (RuleModel)->Unit){
        onButtonClickListener = listener
    }


}