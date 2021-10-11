package com.tlgbltcn.githubsquarerepos.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tlgbltcn.githubsquarerepos.data.repository.GithubRepository
import com.tlgbltcn.githubsquarerepos.feature.list.Item
import com.tlgbltcn.githubsquarerepos.feature.list.Loading
import com.tlgbltcn.githubsquarerepos.feature.list.RepositoryListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: GithubRepository) : ViewModel() {

    private var _item = MutableStateFlow<RepositoryListState>(Loading)
    val item = _item.asStateFlow()

    private val _showSnackBar = MutableLiveData(false)
    val showSnackBar: LiveData<Boolean>
        get() = _showSnackBar

    private val _isBookmarking = MutableLiveData(false)
    val isBookmarking: LiveData<Boolean>
        get() = _isBookmarking

    fun getRepository(id: Long) {
        viewModelScope.launch {
            val item = repository.getRepository(id)
            _item.value = Item(item)
        }
    }

    fun updateBookmark(value: Boolean, id: Long) {
        viewModelScope.launch {
            _isBookmarking.value = true
            repository.updateRepository(value = value, id = id)
            delay(1000)
            _isBookmarking.value = false
            _showSnackBar.value = true
        }
    }

    fun dismissSnackBar() {
        _showSnackBar.value = false
    }
}