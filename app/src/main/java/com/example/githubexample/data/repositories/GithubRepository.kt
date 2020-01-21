package com.example.githubexample.data.repositories

import com.example.githubexample.data.entities.Repos
import com.example.githubexample.data.remote.GithubApi
import com.example.githubexample.utils.UseCaseResult

interface GithubRepository {
    suspend fun getRepositoryList(repoName: String): UseCaseResult<List<Repos>>
}

class GithubRepositoryImpl(val gitApi: GithubApi) : GithubRepository {
    override suspend fun getRepositoryList(repoName: String): UseCaseResult<List<Repos>> {
        return try {
            val result = gitApi.getReposAsync(username = repoName).await()
            UseCaseResult.Success(result)
        } catch (ex: Exception) {
            UseCaseResult.Error(ex)
        }
    }
}