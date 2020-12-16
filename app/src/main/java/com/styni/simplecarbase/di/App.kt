package com.styni.simplecarbase.di

import android.app.Application
import com.styni.simplecarbase.di.components.AppComponent
import com.styni.simplecarbase.di.components.DaggerAppComponent
import com.styni.simplecarbase.di.modules.AppModule

class App : Application() {
    var appComponent: AppComponent? = null
        get() {
            if (field == null) {
                field = DaggerAppComponent.builder()
                    .appModule(AppModule(this))
                    .build()
            }
            return field
        }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: App
    }
}