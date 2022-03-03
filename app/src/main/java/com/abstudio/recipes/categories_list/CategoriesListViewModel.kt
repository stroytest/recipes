package com.abstudio.recipes.categories_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.abstudio.recipes.data.Repository
import com.abstudio.recipes.data.entities.Category


class CategoriesListViewModel(application: Application): AndroidViewModel(application) {


    private val repository: Repository

    val getAllCategories: LiveData<List<Category>>

    init {
        repository = Repository(application)
        getAllCategories = repository.getAllCategories()
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