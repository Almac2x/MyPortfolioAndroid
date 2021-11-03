package com.example.androidpractice.data.project

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "project_table")
data class Project(
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    val projectName: String,
    val projectSubHeader: String,
    val projectDescription: String,
    val projectImage: String
)