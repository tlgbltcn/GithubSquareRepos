package com.tlgbltcn.githubsquarerepos.data.repository

import com.tlgbltcn.githubsquarerepos.data.local.GithubDatabase
import com.tlgbltcn.githubsquarerepos.data.local.RepositoryDao
import com.tlgbltcn.githubsquarerepos.data.model.RepositoryItem
import com.tlgbltcn.githubsquarerepos.data.remote.GithubService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepository @Inject constructor(
    private val service: GithubService,
    private val dao: RepositoryDao
) {

    suspend fun getRepos() = service.getRepos()

    suspend fun hasDatabaseAnyRecord() = dao.getRepositoryCount() > 0

    suspend fun getRepositoriesFromLocal() = dao.getRepositories()

    suspend fun insertRepositories(repositories: List<RepositoryItem>) =
        dao.insertRepositories(repositories = repositories)
}