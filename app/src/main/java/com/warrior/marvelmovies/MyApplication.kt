package com.warrior.marvelmovies

import android.app.Application
import com.warrior.marvelmovies.di.ApplicationComponent
import com.warrior.marvelmovies.di.DaggerApplicationComponent

/**
 * Author: Felipe Guerrero
 */
class MyApplication : Application() {

    val appComponent: ApplicationComponent = DaggerApplicationComponent.create()
}
