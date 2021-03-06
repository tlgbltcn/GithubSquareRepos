package com.tlgbltcn.githubsquarerepos.feature.list

import com.tlgbltcn.githubsquarerepos.data.model.RepositoryItem

sealed class RepositoryListState

object Loading : RepositoryListState()
object Idle : RepositoryListState()
data class Error(val code: Int?, val message: String?) : RepositoryListState()
data class Content(
    val repos: List<RepositoryItem> = listOf()
) : RepositoryListState()
data class Item(
    val item: RepositoryItem
): RepositoryListState()


