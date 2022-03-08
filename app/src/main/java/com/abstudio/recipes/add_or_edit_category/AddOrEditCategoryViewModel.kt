package com.abstudio.recipes.add_or_edit_category

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.abstudio.recipes.data.RecipesDB
import com.abstudio.recipes.data.Repository
import com.abstudio.recipes.data.entities.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddOrEditCategoryViewModel(application: Application): AndroidViewModel(application) {

    private val repository: Repository

    init {
        repository = Repository(application)
    }

    fun verifyDataFromUser(title: String): Boolean {
        return !(title.isEmpty())
    }

    fun addCategory(newCategory: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCategory(newCategory)
        }
    }



}