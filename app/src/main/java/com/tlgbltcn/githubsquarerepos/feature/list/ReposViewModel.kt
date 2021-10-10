package com.tlgbltcn.githubsquarerepos.feature.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tlgbltcn.githubsquarerepos.data.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@HiltViewModel
class ReposViewModel
@Inject constructor(repository: GithubRepository) : ViewModel() {

    private var stateMachine: RepositoryListStateMachine = RepositoryListStateMachine(
        repository = repository,
        scope = viewModelScope
    )

    private var _repos = MutableStateFlow<RepositoryListState>(Loading)
    val repos = _repos.asStateFlow()

    init {
        fetchRepos()
    }

    private fun fetchRepos() {
        viewModelScope.launch {
            stateMachine.state.collect { state ->
                _repos.value = state
            }
        }
    }

    fun dispatch(action: RepositoryListAction) {
        viewModelScope.launch {
            stateMachine.dispatch(action)
        }
    }
}