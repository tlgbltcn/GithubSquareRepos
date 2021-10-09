package com.tlgbltcn.githubsquarerepos.data.repository

import com.tlgbltcn.githubsquarerepos.data.remote.GithubService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepository @Inject constructor(private val service: GithubService) {

    suspend fun getRepos() = service.getRepos()
}