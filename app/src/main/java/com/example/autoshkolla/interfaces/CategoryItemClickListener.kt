package com.example.autoshkolla.interfaces

import android.view.View
import com.example.autoshkolla.model.CategoryModel

interface CategoryItemClickListener {
    fun onCategoryItemClick(view: View, category: CategoryModel)
}