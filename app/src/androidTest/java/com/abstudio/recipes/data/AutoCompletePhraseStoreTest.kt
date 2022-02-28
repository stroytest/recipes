package com.abstudio.recipes.data

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.abstudio.recipes.data.entities.AutoCompletePhrase
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.hasSize
import com.natpryce.hamkrest.isEmpty
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AutoCompletePhraseStoreTest {

    private val db = Room.inMemoryDatabaseBuilder(
        InstrumentationRegistry.getInstrumentation().targetContext,
        RecipesDB::class.java
    ).build()

    private val underTest = db.auto_complete_phrase()

    @Test
    fun loadAll() {

        assertThat(underTest.loadAll(), isEmpty)

        val phraseA = AutoCompletePhrase("A")
        underTest.insert(phraseA)

        val phraseB = AutoCompletePhrase("B")
        underTest.insert(phraseB)

        underTest.loadAll().let {
            assertThat(it, hasSize(equalTo(2)))
            assertThat(it[0].phrase, equalTo(phraseA.phrase))
            assertThat(it[1].phrase, equalTo(phraseB.phrase))
        }
    }

    @Test
    fun insertAndDelete() {
        assertThat(underTest.loadAll(), isEmpty)

        val phraseA = AutoCompletePhrase("A")
        underTest.insert(phraseA)

        underTest.loadAll().let {
            assertThat(it, hasSize(equalTo(1)))
            assertThat(it[0].phrase, equalTo(phraseA.phrase))
        }

        underTest.delete(phraseA)

        assertThat(underTest.loadAll(), isEmpty)
    }

    @Test
    fun update() {

        val phraseA = AutoCompletePhrase("A")
        underTest.insert(phraseA)

        val updated: AutoCompletePhrase = underTest.findById(phraseA.id)

        updated.phrase = "B"

        underTest.update(updated)

        underTest.loadAll().let {
            assertThat(it[0].phrase, equalTo(updated.phrase))
        }
    }
}