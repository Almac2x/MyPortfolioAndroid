package com.example.androidpractice.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.androidpractice.data.project.Project

@Entity(tableName = "profile_table")
data class MyProfile (
    @PrimaryKey(autoGenerate = true)
    var id : Long,
    val firstName :String,
    val lastName : String,
    val position : String,
    val profileImageLoc : String,
    val profileProjects: MutableList<Project>
)