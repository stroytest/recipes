package com.abstudio.recipes.data.stores

import androidx.room.*
import com.abstudio.recipes.data.entities.ShopingCartItem

@Dao
interface ShopingCartItemStore {

    @Query("SELECT * FROM shoping_cart_item")
    fun loadAll(): List<ShopingCartItem>

    @Query("SELECT * FROM shoping_cart_item WHERE id = :id")
    fun findById(id: String): ShopingCartItem

    @Insert
    fun insert(item: ShopingCartItem)

    @Update
    fun update(item: ShopingCartItem)

    @Delete
    fun delete(item: ShopingCartItem)

}