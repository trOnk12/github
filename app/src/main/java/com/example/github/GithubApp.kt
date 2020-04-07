package com.example.github

import android.app.Application
import android.content.Context
import com.example.github.di.component.CoreComponent
import com.example.github.di.component.DaggerCoreComponent
import com.example.github.di.module.ContextModule

class GithubApp : Application() {

    lateinit var coreComponent: CoreComponent

    companion object {
        fun coreComponent(context: Context) =
            (context.applicationContext as GithubApp).coreComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDaggerCoreComponent()
    }

    private fun initDaggerCoreComponent() {
        coreComponent = DaggerCoreComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
    }

}