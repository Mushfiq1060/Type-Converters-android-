package com.example.typeconverters.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "user_table")
data class User (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "user id") var uId: String,
    @ColumnInfo(name = "user name") var name: String,
    @ColumnInfo(name = "date") var date: Date
        )