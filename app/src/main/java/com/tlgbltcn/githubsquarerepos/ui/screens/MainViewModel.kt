package com.tlgbltcn.githubsquarerepos.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tlgbltcn.githubsquarerepos.data.model.RepositoryItem
import com.tlgbltcn.githubsquarerepos.data.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: GithubRepository
) : ViewModel() {

    private var _repos = MutableStateFlow<List<RepositoryItem>>(listOf())
    val repos = _repos.asStateFlow()

    init {
        fetchRepos()
    }

    private fun fetchRepos() {
        viewModelScope.launch {
            repository.getRepos().collect {
                _repos.value = it
            }
        }
    }
}