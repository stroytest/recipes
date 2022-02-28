package com.abstudio.recipes.data

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.abstudio.recipes.data.entities.Amount
import com.abstudio.recipes.data.entities.Ingredient
import com.abstudio.recipes.data.entities.ShopingCartItem
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.hasSize
import com.natpryce.hamkrest.isEmpty
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ShopingCartItemTest {

    private val db = Room.inMemoryDatabaseBuilder(
        InstrumentationRegistry.getInstrumentation().targetContext,
        RecipesDB::class.java
    ).build()

    private val underTest = db.shoping_cart_item()

    @Test
    fun loadAll() {

        assertThat(underTest.loadAll(), isEmpty)

        val ingredient = Ingredient("ingAId")
        val amount = Amount("amAId")

        val shopingCartItem = ShopingCartItem(ingredient.id, amount.id)
        underTest.insert(shopingCartItem)

        underTest.loadAll().let {
            assertThat(it, hasSize(equalTo(1)))
            assertThat(it[0].ingredientId, equalTo(shopingCartItem.ingredientId))
            assertThat(it[0].amountId, equalTo(shopingCartItem.amountId))
        }
    }

    @Test
    fun insertAndDelete() {
        assertThat(underTest.loadAll(), isEmpty)

        val ingredient = Ingredient("ingAId")
        val amount = Amount("amAId")

        val shopingCartItem = ShopingCartItem(ingredient.id, amount.id)
        underTest.insert(shopingCartItem)

        underTest.loadAll().let {
            assertThat(it, hasSize(equalTo(1)))
        }

        underTest.delete(shopingCartItem)

        assertThat(underTest.loadAll(), isEmpty)
    }
}