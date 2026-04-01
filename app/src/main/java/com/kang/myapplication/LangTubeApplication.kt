package com.kang.myapplication

import android.app.Application
import com.kang.myapplication.data.AppContainer
import com.kang.myapplication.data.DefaultAppContainer

class LangTubeApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}
