package dev.julian.minitestdspot

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dev.julian.minitestdspot.data.UserRoomDatabase
import dev.julian.minitestdspot.repository.UserRepository

@HiltAndroidApp
class UserApplication : Application() {

    /*val database by lazy { UserRoomDatabase.getDatabase(this) }
    val repository by lazy { UserRepository() }*/
}