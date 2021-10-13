package com.tlgbltcn.githubsquarerepos.data.repository

import com.tlgbltcn.githubsquarerepos.data.local.RepositoryDao
import com.tlgbltcn.githubsquarerepos.data.model.RepositoryItem
import com.tlgbltcn.githubsquarerepos.data.remote.GithubService
import com.tlgbltcn.githubsquarerepos.di.IoDispatcher
import com.tlgbltcn.githubsquarerepos.feature.list.Content
import com.tlgbltcn.githubsquarerepos.feature.list.Error
import com.tlgbltcn.githubsquarerepos.feature.list.Loading
import com.tlgbltcn.githubsquarerepos.util.ioHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GithubRepository @Inject constructor(
    private val service: GithubService,
    private val dao: RepositoryDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun getRepos() = flow {
        ioHandler(
            shouldFetch = { hasDatabaseAnyRecord().not() },
            onCall = {
                emit(Loading)
                service.getRepos()
            },
            onStoreData = this@GithubRepository::insertRepositories,
            onQuery = {
                emit(Loading)
                getRepositoriesFromLocal()
            },
            onSuccess = { list ->
                emit(
                    Content(repos = list)
                )
            },
            onFailure = { code, message ->
                emit(Error(code = code, message = message))
            })
    }.flowOn(dispatcher)

    private fun hasDatabaseAnyRecord() = dao.getRepositoryCount() > 0

    private fun getRepositoriesFromLocal() = dao.getRepositories()

    private fun insertRepositories(repositories: List<RepositoryItem>) =
        dao.insertRepositories(repositories = repositories)

    suspend fun getRepository(id: Long) = dao.getRepository(id)

    suspend fun updateRepository(value: Boolean, id: Long) =
        dao.updateRepository(value = value, id = id)
}