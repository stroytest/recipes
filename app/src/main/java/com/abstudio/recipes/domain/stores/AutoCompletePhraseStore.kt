package com.abstudio.recipes.domain.stores

import androidx.room.*
import com.abstudio.recipes.domain.entities.AutoCompletePhrase

@Dao
interface AutoCompletePhraseStore {

    @Query("SELECT * FROM dictionary ORDER BY phrase")
    fun loadAll(): List<AutoCompletePhrase>

    @Query("SELECT * FROM dictionary WHERE id = :id")
    fun findById(id: String): AutoCompletePhrase

    @Insert
    fun insert(phrase: AutoCompletePhrase)

    @Update
    fun update(phrase: AutoCompletePhrase)

    @Delete
    fun delete(phrase: AutoCompletePhrase)
}