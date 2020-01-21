package com.example.githubexample.module

import com.example.githubexample.data.remote.GithubApi
import com.example.githubexample.data.repositories.GithubRepository
import com.example.githubexample.data.repositories.GithubRepositoryImpl
import com.example.githubexample.presentation.main.MainViewModel
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.github.com/"

val appModules = module {
    single {
        createWebService<GithubApi>(
                baseUrl = BASE_URL
        )
    }
    factory<GithubRepository> { GithubRepositoryImpl(gitApi = get()) }
    viewModel { MainViewModel(gitRepository = get()) }
}

inline fun <reified T> createWebService(baseUrl: String): T {
    val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    return retrofit.create(T::class.java)
}