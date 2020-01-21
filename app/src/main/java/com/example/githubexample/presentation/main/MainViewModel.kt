package com.example.githubexample.presentation.main

import androidx.lifecycle.MutableLiveData
import com.example.githubexample.data.entities.Repos
import com.example.githubexample.data.repositories.GithubRepository
import com.example.githubexample.presentation.base.BaseViewModel
import com.example.githubexample.utils.SingleLiveEvent
import com.example.githubexample.utils.UseCaseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val gitRepository: GithubRepository) : BaseViewModel() {

    val showLoading = MutableLiveData<Boolean>()
    val reposList = MutableLiveData<List<Repos>>()
    val showError = SingleLiveEvent<String>()
    val navigateToDetail = SingleLiveEvent<Repos>()

    fun loadRepos(repoName: String) {
        showLoading.value = true
        launch {
            val result = withContext(Dispatchers.IO) { gitRepository.getRepositoryList(repoName) }
            showLoading.value = false
            when (result) {
                is UseCaseResult.Success -> reposList.value = result.data
                is UseCaseResult.Error -> showError.value = result.exception.message
            }
        }
    }

    fun reposClicked(repos: Repos) {
        navigateToDetail.value = repos
    }
}