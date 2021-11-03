package com.example.androidpractice.data.project

import androidx.lifecycle.LiveData


class ProjectRepository( private val projectDao : ProjectDao)  {

    val readAllData : LiveData<List<Project>> = projectDao.readAllData()

    suspend fun addProject(project : Project){
        projectDao.addProject(project)
    }

    suspend fun updateProject(project:Project){
        projectDao.updateProject(project)
    }

}