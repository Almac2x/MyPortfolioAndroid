package com.example.androidpractice.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "projects")
data class Project(
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    val projectName: String,
    val projectSubHeader: String,
    val projectDescription: String,
    val projectImage: String
)