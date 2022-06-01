package dev.julian.minitestdspot.repository

import androidx.annotation.WorkerThread
import dev.julian.minitestdspot.api.UserService
import dev.julian.minitestdspot.data.User
import dev.julian.minitestdspot.data.UserDao
import dev.julian.minitestdspot.data.UserPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class UserRepository @Inject constructor(private val service: UserService) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.


    fun userPagingSource(query: String) = UserPagingSource(service,query)

    /*fun loadUser(
        onStart: () -> Unit,
        onCompletion: () -> Unit,
        onError: (String) -> Unit
    ) = flow {
        val allUsers: Flow<List<User>> = userDao.getUsers()

        if (allUsers.count() == 0){
            UserPagingSource(userService, "name")

        } else
            emit(allUsers)
    }.onStart { onStart() }.onCompletion { onCompletion() }.flowOn(Dispatchers.IO)*/

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    /*@Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertAll(users: List<User>) {
        userDao.insertAll(users)
    }*/
}