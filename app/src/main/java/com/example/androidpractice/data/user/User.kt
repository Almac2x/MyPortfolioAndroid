package com.example.androidpractice.data.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName :String,
    val lastName : String,
    val position : String,
    val profileImageLoc : String,
    // val profileProjects: MutableList<Project> // need to make this seriazizable
)

