package com.abstudio.recipes.domain

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.abstudio.recipes.domain.entities.Category
import com.abstudio.recipes.domain.entities.Recipe
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.hasSize
import com.natpryce.hamkrest.isEmpty
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecipeStoreTest {

    private val db = Room.inMemoryDatabaseBuilder(
        InstrumentationRegistry.getInstrumentation().targetContext,
        RecipesDB::class.java
    ).build()

    private val underTest = db.recipes()

    @Test
    fun insert() {
        assertThat(underTest.loadAll(), isEmpty)

        val recipe = Recipe("RecipeA")

        underTest.insert(recipe)

        underTest.loadAll().let {
            assertThat(it, hasSize(equalTo(1)))
            assertThat(it[0], equalTo(recipe))
        }
    }

    @Test
    fun getById() {

        val recipe = Recipe("RecipeA")

        underTest.insert(recipe)

        val fromDB: Recipe = underTest.findById(recipe.id)

        assertThat(fromDB, equalTo(recipe))
    }

    @Test
    fun update() {

        val recipe = Recipe("RecipeA")

        underTest.insert(recipe)

        val updated: Recipe = underTest.findById(recipe.id)

        updated.name = "RecipeB"

        underTest.update(updated)

        underTest.loadAll().let {
            assertThat(it, hasSize(equalTo(1)))
            assertThat(it[0], equalTo(updated))
        }
    }

    @Test
    fun delete() {

        val recipe = Recipe("RecipeA")

        underTest.insert(recipe)

        underTest.delete(recipe)

        assertThat(underTest.loadAll(), isEmpty)
    }

}