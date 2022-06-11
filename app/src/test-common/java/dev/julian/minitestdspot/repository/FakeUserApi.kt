package dev.julian.minitestdspot.repository

import dev.julian.minitestdspot.api.UserService
import dev.julian.minitestdspot.data.InfoService
import dev.julian.minitestdspot.data.User
import dev.julian.minitestdspot.data.UserResponse

class FakeUserApi : UserService {

    override suspend fun loadUser(page: Int,
                                  results: Int,
                                  seed: String,
                                  inc: String,      // picture, name, email, location
    ) : UserResponse {
        val users : MutableList<User> = arrayListOf()
        users.add(UserFactory().createUser())                         //[UserFactory().createUser()].toList()
        return UserResponse(users, InfoService("",1,1))
    }

}