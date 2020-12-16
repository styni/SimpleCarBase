package com.styni.simplecarbase.di.components

import com.styni.simplecarbase.di.modules.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
}