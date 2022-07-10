package com.example.autoshkolla.adapters.categoryB

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.autoshkolla.databinding.ItemBSignsBinding
import com.example.autoshkolla.model.SignModel
import com.squareup.picasso.Picasso

class CategoryBSignsAdapter: RecyclerView.Adapter<CategoryBSignsAdapter.SignDataViewHolder>() {

    private val  differCallBack = object : DiffUtil.ItemCallback<SignModel>(){
        override fun areItemsTheSame(oldItem: SignModel, newItem: SignModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SignModel, newItem: SignModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SignDataViewHolder {
        val binding = ItemBSignsBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return SignDataViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    override fun onBindViewHolder(holder: SignDataViewHolder, position: Int) {
        val user = differ.currentList[position]
        holder.bind(user)
    }

    inner class SignDataViewHolder(
        private val binding: ItemBSignsBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(signModel: SignModel) {
            Picasso.get().load(signModel.url).into(binding.signBImg)
            binding.imgBTitle.text = signModel.title
            binding.imgBDescription.text = signModel.description
        }
    }

    private var onButtonClickListener :((SignModel)->Unit)?=null

    fun setOnButtonClickListener(listener : (SignModel)->Unit){
        onButtonClickListener = listener
    }
}