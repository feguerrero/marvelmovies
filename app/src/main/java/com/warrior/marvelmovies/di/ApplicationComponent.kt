package com.warrior.marvelmovies.di

import com.warrior.marvelmovies.view.MainActivity
import dagger.Component

/**
 * Author: Felipe Guerrero
 */
@Component(modules = [AppModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)
}
