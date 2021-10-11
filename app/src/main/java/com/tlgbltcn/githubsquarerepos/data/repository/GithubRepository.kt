package com.tlgbltcn.githubsquarerepos.data.repository

import com.tlgbltcn.githubsquarerepos.data.local.RepositoryDao
import com.tlgbltcn.githubsquarerepos.data.model.RepositoryItem
import com.tlgbltcn.githubsquarerepos.data.remote.GithubService
import com.tlgbltcn.githubsquarerepos.di.IoDispatcher
import com.tlgbltcn.githubsquarerepos.feature.list.Content
import com.tlgbltcn.githubsquarerepos.feature.list.Error
import com.tlgbltcn.githubsquarerepos.feature.list.Loading
import com.tlgbltcn.githubsquarerepos.feature.list.RepositoryListState
import com.tlgbltcn.githubsquarerepos.util.ioHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepository @Inject constructor(
    private val service: GithubService,
    private val dao: RepositoryDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun getRepos() = flow {
        var state: RepositoryListState = Loading
        ioHandler(
            shouldFetch = { hasDatabaseAnyRecord().not() },
            onCall = service::getRepos,
            onStoreData = this@GithubRepository::insertRepositories,
            onQuery = this@GithubRepository::getRepositoriesFromLocal,
            onSuccess = { list ->
                Content(repos = list).also { state = it }
            },
            onFailure = { code, message ->
                Error(code = code, message = message).also { state = it }
            })
        emit(state)
    }.flowOn(dispatcher)

    private suspend fun hasDatabaseAnyRecord() = dao.getRepositoryCount() > 0

    private suspend fun getRepositoriesFromLocal() = dao.getRepositories()

    private suspend fun insertRepositories(repositories: List<RepositoryItem>) =
        dao.insertRepositories(repositories = repositories)

    suspend fun getRepository(id: Long) = dao.getRepository(id)

    suspend fun updateRepository(value: Boolean, id: Long) =
        dao.updateRepository(value = value, id = id)
}