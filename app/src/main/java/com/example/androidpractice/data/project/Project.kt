package com.example.androidpractice.data.project

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "project_table")
data class Project(
    @PrimaryKey(autoGenerate = true)
    var id : Long,
    val projectName: String,
    val projectSubHeader: String,
    val projectDescription: String,
    val projectImage: String
):Parcelable