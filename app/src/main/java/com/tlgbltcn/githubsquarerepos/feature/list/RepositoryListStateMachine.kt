package com.tlgbltcn.githubsquarerepos.feature.list

import com.freeletics.flowredux.dsl.ChangeState
import com.freeletics.flowredux.dsl.FlowReduxStateMachine
import com.freeletics.flowredux.dsl.OverrideState
import com.tlgbltcn.githubsquarerepos.data.repository.GithubRepository
import com.tlgbltcn.githubsquarerepos.util.AndroidFlowReduxLogger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import java.lang.Exception

@ExperimentalCoroutinesApi
@FlowPreview
class RepositoryListStateMachine constructor(
    val repository: GithubRepository,
    val scope: CoroutineScope
) :
    FlowReduxStateMachine<RepositoryListState, RepositoryListAction>(
        initialState = Loading,
        scope = scope,
        logger = AndroidFlowReduxLogger
    ) {

    init {
        spec {
            inState<Loading> {
                onEnter(::shouldFetch)
            }

            inState<CallRemote> {
                onEnter {
                    try {
                        val result = repository.getRepos()
                        if (result.isSuccessful) {
                            OverrideState(
                                StoreData(
                                    list = result.body() ?: listOf()
                                )
                            )
                        } else {
                            OverrideState(
                                Error(
                                    result.code(), result.message()
                                )
                            )
                        }
                    } catch (e: Exception) {
                        OverrideState(
                            Error(
                                1, ""
                            )
                        )
                    }
                }
            }

            inState<StoreData> {
                onEnter { state ->
                    repository.insertRepositories(state.list)
                    OverrideState(
                        Query
                    )
                }
            }

            inState<Query> {
                onEnter {
                    val repos = getRepositoriesFromLocal()
                    OverrideState(
                        Content(
                            list = repos
                        )
                    )
                }
            }

            inState<Error> {
                on<RetryAction> { _, _ ->
                    OverrideState(
                        Error(
                            1, ""
                        )
                    )
                }
            }

            inState<Content> {
                on { action: SuccessAction, state: Content ->
                    OverrideState(newState = Content(state.list))
                }
            }
        }
    }

    private suspend fun hasDatabaseAnyRecord(): Boolean = repository.hasDatabaseAnyRecord()

    private suspend fun getRepositoriesFromLocal() = repository.getRepositoriesFromLocal()

    private suspend fun shouldFetch(state: RepositoryListState): ChangeState<RepositoryListState> =
        OverrideState(
            if (hasDatabaseAnyRecord().not()) CallRemote
            else Query
        )
}