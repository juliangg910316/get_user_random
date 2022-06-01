package dev.julian.minitestdspot.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/*
* The Data Access Object for the User class
*/
@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getUsers(): Flow<List<User>>

    @Query("SELECT * FROM users WHERE user_id = :userId")
    fun getUser(userId: String): Flow<User>

    /*@Query("SELECT id, name, email, thumbnail FROM users")
    fun getUsersMinimal(): Flow<List<UserMinimal>>*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<User>)
}