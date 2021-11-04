package com.example.androidpractice.data.project

import androidx.lifecycle.LiveData
import androidx.room.PrimaryKey


class ProjectRepository( private val projectDao : ProjectDao)  {

    val readAllData : LiveData<List<Project>> = projectDao.readAllData()

    suspend fun addProject(project : Project){
        projectDao.addProject(project)
    }

    suspend fun updateProject(project:Project){
        projectDao.updateProject(project)
    }

    suspend fun deleteProject(project:Project){
        projectDao.deleteProject(project)
    }

    suspend fun deleteAllProjects(){
        projectDao.deleteAllProjects()
    }

}