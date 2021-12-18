package io.github.iampjeetsingh.assignment.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.github.iampjeetsingh.assignment.models.User

@Dao
interface UserDao {
    @Insert
    suspend fun addUsers(users: List<User>)

    @Query("SELECT * FROM users")
    fun getUsers() : LiveData<List<User>>
}