package com.tlgbltcn.githubsquarerepos.data.repository

import com.tlgbltcn.githubsquarerepos.data.model.RepositoryItem
import com.tlgbltcn.githubsquarerepos.data.remote.GithubService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepository @Inject constructor(private val service: GithubService) {

    fun getRepos() = flow<List<RepositoryItem>> {
        val repos = arrayListOf<RepositoryItem>()
        val result = service.getRepos()
        if (result.isSuccessful) {
            result.body()?.let {
                repos.addAll(
                    it
                )
            }
        }

        emit(repos)

    }.flowOn(Dispatchers.IO)
}