package com.tlgbltcn.githubsquarerepos.feature.list

sealed class RepositoryListAction

object RetryAction: RepositoryListAction()

object SuccessAction: RepositoryListAction()