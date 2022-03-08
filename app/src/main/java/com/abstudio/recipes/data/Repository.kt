package com.abstudio.recipes.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.abstudio.recipes.data.entities.Category

class Repository(application: Application) {

    private val categoryStore = RecipesDB.getDatabase(application).categories()


    fun getAllCategories(): LiveData<List<Category>> {
        return categoryStore.getAllCategories()
    }

    fun addCategory(newCategory: Category) {
        categoryStore.insert(newCategory)
    }

    fun deleteCategory(category: Category) {
        categoryStore.delete(category)
    }


}