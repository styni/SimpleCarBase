package com.styni.simplecarbase.di.modules

import android.app.Application
import com.styni.simplecarbase.di.ForApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    @ForApplication
    fun provideApplicationContext(): Application = application

}