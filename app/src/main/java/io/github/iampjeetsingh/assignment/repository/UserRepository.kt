package io.github.iampjeetsingh.assignment.repository

import androidx.lifecycle.LiveData
import io.github.iampjeetsingh.assignment.db.UserDao
import io.github.iampjeetsingh.assignment.models.User

class UserRepository(private val userDao: UserDao) {
    fun getUsers(): LiveData<List<User>>{
        return userDao.getUsers()
    }

    suspend fun getUsers(users: List<User>){
        return userDao.addUsers(users)
    }

}