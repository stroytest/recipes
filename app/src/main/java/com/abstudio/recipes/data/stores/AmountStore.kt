package com.abstudio.recipes.data.stores

import androidx.room.*
import com.abstudio.recipes.data.entities.Amount

@Dao
interface AmountStore {

    @Query("SELECT * FROM amounts ORDER BY name")
    fun loadAll(): List<Amount>

    @Query("SELECT * FROM amounts WHERE id = :id")
    fun findById(id: String): Amount

    @Insert
    fun insert(amount: Amount)

    @Update
    fun update(amount: Amount)

    @Delete
    fun delete(amount: Amount)

}