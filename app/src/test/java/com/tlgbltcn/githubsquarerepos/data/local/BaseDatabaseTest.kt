package com.tlgbltcn.githubsquarerepos.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [29])
open class BaseDatabaseTest {

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    lateinit var database: GithubDatabase

    @Before
    fun createDatabase() = runBlocking {
        database =
            Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                GithubDatabase::class.java
            )
                .allowMainThreadQueries()
                .build()

        fillDao()
    }

    @After
    fun closeDb() {
        database.close()
    }

    open fun fillDao() {
        // no-op
    }
}