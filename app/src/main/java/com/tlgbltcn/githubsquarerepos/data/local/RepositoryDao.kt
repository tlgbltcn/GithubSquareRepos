package com.tlgbltcn.githubsquarerepos.data.local

import androidx.room.*
import com.tlgbltcn.githubsquarerepos.data.model.RepositoryItem

@Dao
interface RepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepositories(repositories: List<RepositoryItem>)

    @Query("SELECT COUNT(*) FROM repositories")
    fun getRepositoryCount(): Int

    @Query("SELECT * FROM repositories")
    fun getRepositories(): List<RepositoryItem>

    @Query("SELECT * FROM repositories WHERE repoId=:id")
    suspend fun getRepository(id: Long): RepositoryItem

    @Query("UPDATE repositories SET isBookmarked=:value WHERE repoId=:id")
    suspend fun updateRepository(value: Boolean, id: Long)
}