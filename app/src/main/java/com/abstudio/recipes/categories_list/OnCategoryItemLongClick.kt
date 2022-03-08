package com.abstudio.recipes.categories_list

import com.abstudio.recipes.data.entities.Category

interface OnCategoryItemLongClick {
    fun editCategory(category: Category)
    fun deleteCategory(category: Category)
}