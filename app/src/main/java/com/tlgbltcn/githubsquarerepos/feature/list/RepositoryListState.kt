package com.tlgbltcn.githubsquarerepos.feature.list

import com.tlgbltcn.githubsquarerepos.data.model.RepositoryItem

sealed class RepositoryListState

object Loading : RepositoryListState()
object CallRemote: RepositoryListState()
data class StoreData(
    val list: List<RepositoryItem> = listOf()
) : RepositoryListState()
object Query: RepositoryListState()
data class Error(val code: Int?, val message: String?) : RepositoryListState()
data class Content(
    val list: List<RepositoryItem> = listOf()
) : RepositoryListState()


