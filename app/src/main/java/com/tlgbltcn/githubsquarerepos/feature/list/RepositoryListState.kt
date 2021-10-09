package com.tlgbltcn.githubsquarerepos.feature.list

import com.tlgbltcn.githubsquarerepos.data.model.RepositoryItem

sealed class RepositoryListState

object Loading : RepositoryListState()
data class Error(val code: Int?, val message: String?) : RepositoryListState()
data class RepositoriesContent(
    val list: List<RepositoryItem>? = listOf()
) : RepositoryListState()


