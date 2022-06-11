package dev.julian.minitestdspot

import android.app.Application
import android.content.Context
import androidx.annotation.VisibleForTesting
import dev.julian.minitestdspot.api.UserService
import dev.julian.minitestdspot.repository.UserRepository

interface ServiceLocator {
    companion object {
        private val LOCK = Any()
        private var instance: ServiceLocator? = null
        fun instance(context: Context): ServiceLocator {
            synchronized(LOCK) {
                if (instance == null) {
                    instance = DefaultServiceLocator()
                }
                return instance!!
            }

        }

        /**
         * Allows tests to replace the default implementations.
         */
        @VisibleForTesting
        fun swap(locator: ServiceLocator) {
            instance = locator
        }

    }

    fun getRepository(): UserRepository

    fun getUserApi(): UserService

}

/**
 * default implementation of ServiceLocator that uses production endpoints.
 */
open class DefaultServiceLocator() : ServiceLocator {

    override fun getRepository() : UserRepository {
        return UserRepository(getUserApi())
    }

    override fun getUserApi() : UserService = UserService.create()
}