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
class ReposViewModel @Inject constructor(private val repository: GithubRepository) : ViewModel() {

    private var _repos = MutableStateFlow<RepositoryListState>(Loading)
    val repos = _repos.asStateFlow()

    fun fetchRepos() {
        viewModelScope.launch {
            repository.getRepos().collect {
                _repos.value = it
            }
        }
    }
}