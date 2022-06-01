package dev.julian.minitestdspot

import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import dev.julian.minitestdspot.repository.UserRepository
import dev.julian.minitestdspot.viewmodels.ViewModelFactory

object Injection {

    /*private fun provideUserRepository() : UserRepository = UserRepository()

    fun provideViewModelFactory(owner: SavedStateRegistryOwner): ViewModelProvider.Factory {
        return ViewModelFactory(owner, provideUserRepository())
    }*/
}