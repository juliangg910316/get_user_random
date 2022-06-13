package dev.julian.minitestdspot.repository

import dev.julian.minitestdspot.api.UserService
import dev.julian.minitestdspot.data.UserPagingSource
import javax.inject.Inject


class UserRepository @Inject constructor(private val service: UserService) {

    fun userPagingSource(query: String) = UserPagingSource(service,query)

}