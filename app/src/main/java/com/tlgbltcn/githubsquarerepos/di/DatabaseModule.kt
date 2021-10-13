package com.tlgbltcn.githubsquarerepos.di

import android.content.Context
import androidx.room.Room
import com.tlgbltcn.githubsquarerepos.data.local.GithubDatabase
import com.tlgbltcn.githubsquarerepos.data.local.RepositoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext context: Context): GithubDatabase =
        Room
            .databaseBuilder(
                context,
                GithubDatabase::class.java,
                GithubDatabase.DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun providesRoundDao(database: GithubDatabase): RepositoryDao = database.repositoryDao()
}