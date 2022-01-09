package com.abstudio.recipes.domain

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.abstudio.recipes.domain.entities.Ingredient
import com.abstudio.recipes.domain.entities.Recipe
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.hasSize
import com.natpryce.hamkrest.isEmpty
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class IngredientStoreTest {


    private val db = Room.inMemoryDatabaseBuilder(
        InstrumentationRegistry.getInstrumentation().targetContext,
        RecipesDB::class.java
    ).build()

    private val underTest = db.ingredients()


    @Test
    fun insertAndDelete() {
        assertThat(underTest.loadAll(), isEmpty)

        val entity = Ingredient("IngredientA")

        underTest.insert(entity)

        underTest.loadAll().let {
            assertThat(it, hasSize(equalTo(1)))
            assertThat(it[0], equalTo(entity))
        }

        underTest.delete(entity)

        assertThat(underTest.loadAll(), isEmpty)
    }

    @Test
    fun getById() {

        val entity = Ingredient("IngredientA")

        underTest.insert(entity)

        val fromDB: Ingredient = underTest.findById(entity.id)

        assertThat(fromDB, equalTo(entity))
    }

    @Test
    fun update() {

        val entity = Ingredient("IngredientA")

        underTest.insert(entity)

        val updated: Ingredient = underTest.findById(entity.id)

        updated.name = "IngredientB"

        underTest.update(updated)

        underTest.loadAll().let {
            assertThat(it, hasSize(equalTo(1)))
            assertThat(it[0], equalTo(updated))
        }
    }


}