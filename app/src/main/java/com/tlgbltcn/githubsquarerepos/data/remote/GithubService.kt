package com.tlgbltcn.githubsquarerepos.data.remote

import com.tlgbltcn.githubsquarerepos.data.model.RepositoryItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("{org}/repos")
    suspend fun getRepos(@Path("org") org: String = "square"): Response<List<RepositoryItem>>
}