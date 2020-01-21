package com.example.githubexample

import android.app.Application
import com.example.githubexample.module.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GithubApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@GithubApp)
            modules(appModules)
        }
    }
}