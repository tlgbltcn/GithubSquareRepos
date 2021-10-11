package com.tlgbltcn.githubsquarerepos.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tlgbltcn.githubsquarerepos.data.model.RepositoryItem

@Database(entities = [RepositoryItem::class], version = 1)
abstract class GithubDatabase : RoomDatabase() {

    abstract fun repositoryDao(): RepositoryDao

    companion object {
        const val DATABASE_NAME = "database-github"
    }
}