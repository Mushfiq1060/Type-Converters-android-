package com.example.typeconverters.repository

import com.example.typeconverters.db.UserDao
import com.example.typeconverters.model.User

class UserRepository(private val dao: UserDao) {

    fun insert(user: User) = dao.insert(user)

    fun search(id: String) = dao.search(id)


}