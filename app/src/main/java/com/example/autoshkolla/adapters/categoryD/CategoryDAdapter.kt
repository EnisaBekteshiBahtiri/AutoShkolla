package com.example.autoshkolla.adapters.categoryD

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.autoshkolla.databinding.RowCategoryDItemBinding
import com.example.autoshkolla.model.CategoryModel
import kotlinx.android.synthetic.main.row_category_d_item.view.*

class CategoryDAdapter : RecyclerView.Adapter<CategoryDAdapter.CategoryDViewHolder>() {

    private lateinit var binding: RowCategoryDItemBinding
    private var selectedItem = -1

    inner class CategoryDViewHolder(binding: RowCategoryDItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(categoryModel: CategoryModel, position: Int) {
            binding.title4.text = categoryModel.title
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryDViewHolder {
        binding = RowCategoryDItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoryDViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: CategoryDViewHolder, position: Int) {
        val category = differ.currentList[position]
        holder.bind(category,position)
    }

    private var onItemClickListener :((CategoryModel)->Unit)?=null

    fun setOnItemClickListener(listener : (CategoryModel)->Unit){
        onItemClickListener = listener
    }
}