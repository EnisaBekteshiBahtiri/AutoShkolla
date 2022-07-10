package com.example.autoshkolla.adapters.categoryC

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.autoshkolla.databinding.RowCategoryCItemBinding
import com.example.autoshkolla.model.CategoryModel
import kotlinx.android.synthetic.main.row_category_c_item.view.*

class CategoryCAdapter : RecyclerView.Adapter<CategoryCAdapter.CategoryCViewHolder>() {
    private lateinit var binding: RowCategoryCItemBinding
    private var selectedItem = -1

    inner class CategoryCViewHolder(binding: RowCategoryCItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(categoryModel: CategoryModel, position: Int) {
            binding.title3.text = categoryModel.title
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

    private val  differCallBack = object : DiffUtil.ItemCallback<CategoryModel>(){
        override fun areItemsTheSame(
            oldItem: CategoryModel,
            newItem: CategoryModel
        ): Boolean {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryCViewHolder {
        binding = RowCategoryCItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoryCViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: CategoryCViewHolder, position: Int) {
        val category = differ.currentList[position]
        holder.bind(category,position)
    }
    private var onItemClickListener :((CategoryModel)->Unit)?=null

    fun setOnItemClickListener(listener : (CategoryModel)->Unit){
        onItemClickListener = listener
    }
}