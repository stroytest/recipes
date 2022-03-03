package com.abstudio.recipes.utils

import com.abstudio.recipes.data.entities.Category
import java.util.ArrayList

fun getCategories(): List<Category> {
    val categories = ArrayList<Category>()

    for (i in 0 until 5) {
        val category = Category("Category $i")
        categories.add(category)
    }

    return categories
}