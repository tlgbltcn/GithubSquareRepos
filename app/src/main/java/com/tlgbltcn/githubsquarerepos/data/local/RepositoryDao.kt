package com.tlgbltcn.githubsquarerepos.data.local

import androidx.room.*
import com.tlgbltcn.githubsquarerepos.data.model.RepositoryItem

@Dao
interface RepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepositories(repositories: List<RepositoryItem>)

    @Update
    suspend fun updateRepositories(repositories: List<RepositoryItem>)

    @Query("SELECT COUNT(*) FROM repositories")
    suspend fun getRepositoryCount(): Int

    @Query("SELECT * FROM repositories")
    suspend fun getRepositories(): List<RepositoryItem>
}