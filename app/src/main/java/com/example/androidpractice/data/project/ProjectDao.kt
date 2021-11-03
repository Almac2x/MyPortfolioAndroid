package com.example.androidpractice.data.project

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ProjectDao {

    //Insert annotation of Room. This inserts the data in the database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProject(project: Project)

    //Query annotation of Room. This is a query that gets all the values in the project table
    // and orders it by their id in ascending order
    @Query(value = "SELECT * FROM project_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Project>>
}