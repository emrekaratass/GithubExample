package com.example.githubexample.data.remote

import com.example.githubexample.data.entities.Repos
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("users/{user}/repos")
    fun getReposAsync(@Path("user") username: String): Deferred<List<Repos>>
}
