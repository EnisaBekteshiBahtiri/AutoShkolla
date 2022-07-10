package com.example.autoshkolla.adapters.categoryB

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.autoshkolla.databinding.RowCategoryBItemBinding
import com.example.autoshkolla.model.CategoryModel
import kotlinx.android.synthetic.main.row_category_b_item.view.*

class CategoryBAdapter : RecyclerView.Adapter<CategoryBAdapter.CategoriesViewHolder>() {
    private var selectedItem = -1
    private lateinit var binding: RowCategoryBItemBinding

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        binding = RowCategoryBItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoriesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val category = differ.currentList[position]
        holder.bind(category,position)

    }

    inner class CategoriesViewHolder(binding: RowCategoryBItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(categoryModel: CategoryModel, position: Int) {
            binding.title2.text = categoryModel.title
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