package com.example.typeconverters.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.typeconverters.model.User


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: User)

    @Query("SELECT * FROM user_table WHERE `user id` LIKE :id")
    fun search(id: String): User?


}