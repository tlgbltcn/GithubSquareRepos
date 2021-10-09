package com.tlgbltcn.githubsquarerepos.feature.list

import com.freeletics.flowredux.dsl.ChangeState
import com.freeletics.flowredux.dsl.FlowReduxStateMachine
import com.freeletics.flowredux.dsl.OverrideState
import com.tlgbltcn.githubsquarerepos.data.repository.GithubRepository
import com.tlgbltcn.githubsquarerepos.util.apiCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
class RepositoryListStateMachine constructor(
    val repository: GithubRepository,
    val scope: CoroutineScope
) :
    FlowReduxStateMachine<RepositoryListState, RepositoryListAction>(
        initialState = Loading,
        scope = scope
    ) {

    init {
        spec {
            inState<Loading> {
                onEnter(::fetchRepos)
            }

            inState<RepositoriesContent> {
                on { action: SuccessAction, state: RepositoriesContent ->
                    OverrideState(newState = RepositoriesContent(state.list))
                }
            }
        }
    }

    private suspend fun fetchRepos(state: RepositoryListState): ChangeState<RepositoryListState> =
        apiCall(
            onCall = {
                repository.getRepos()
            },
            onSuccess = { item ->
                OverrideState(
                    newState = RepositoriesContent(
                        list = item
                    )
                )
            },
            onFailure = { code, message ->
                OverrideState(
                    newState = Error(code = code, message = message)
                )
            }
        )
}