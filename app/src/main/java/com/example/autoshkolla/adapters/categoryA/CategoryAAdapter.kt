package com.example.autoshkolla.adapters.categoryA

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.autoshkolla.model.CategoryModel
import com.example.autoshkolla.databinding.RowCategoryAItemBinding

class CategoryAAdapter : RecyclerView.Adapter<CategoryAAdapter.CategoryAViewHolder>() {
    private var selectedItem = -1
    private lateinit var binding: RowCategoryAItemBinding

    private val  differCallBack = object : DiffUtil.ItemCallback<CategoryModel>(){
        override fun areItemsTheSame(
            oldItem: CategoryModel,
            newItem: CategoryModel
        ): Boolean {
            if (oldItem.id != newItem.id) {
                selectedItem = -1
            }
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: CategoryModel,
            newItem: CategoryModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAViewHolder {
        binding = RowCategoryAItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoryAViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: CategoryAViewHolder, position: Int) {
        val category = differ.currentList[position]
        holder.bind(category,position)

    }

    inner class CategoryAViewHolder(binding:RowCategoryAItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(categoryModel: CategoryModel, position: Int) {
            binding.title.text = categoryModel.title
            binding.root.setOnClickListener {
                selectedItem = position

                if(selectedItem == 0){

                }
                notifyDataSetChanged()
                onItemClickListener?.let {
                    it(categoryModel)
                }
            }

        }
    }

    private var onItemClickListener :((CategoryModel)->Unit)?=null

    fun setOnItemClickListener(listener : (CategoryModel)->Unit){
        onItemClickListener = listener
    }
}