package com.abstudio.recipes.categories_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.abstudio.recipes.data.Repository
import com.abstudio.recipes.data.entities.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CategoriesListViewModel(application: Application): AndroidViewModel(application) {


    private val repository: Repository

    val getAllCategories: LiveData<List<Category>>
    val emptyDataBase: MutableLiveData<Boolean> = MutableLiveData(false)

    init {
        repository = Repository(application)
        getAllCategories = repository.getAllCategories()
    }

    fun checkIfDataBaseEmpty(categories: List<Category>) {
        emptyDataBase.value = categories.isEmpty()
    }

    fun deleteCategory(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCategory(category)
        }
    }

    /*
    fun insertData(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(toDoData)
        }
    }

    fun updateData(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(toDoData)
        }
    }

    fun deleteItem(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(toDoData)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

    fun searchDataBase(searchQuery: String): LiveData<List<ToDoData>> {
        return repository.searchDataBase(searchQuery)
    }*/
}