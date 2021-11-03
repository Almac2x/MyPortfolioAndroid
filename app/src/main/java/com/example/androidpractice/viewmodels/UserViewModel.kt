package com.example.androidpractice.viewmodels

import android.app.Application
import android.service.autofill.UserData
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.androidpractice.data.user.User
import com.example.androidpractice.data.database.UserDatabase
import com.example.androidpractice.data.project.Project
import com.example.androidpractice.data.project.ProjectRepository
import com.example.androidpractice.data.user.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel (application: Application) : AndroidViewModel(application) {

    private val readAllData : LiveData<List<User>>
    private val userRepository: UserRepository

    private val projectRepository: ProjectRepository
     val readAllProjects : LiveData<List<Project>>
    fun get() = run { readAllProjects.value?.toMutableList() }

    init {
        //gets the dao of all data types in the Database
        val userDao = UserDatabase.getDatabase(application).userDao()
        val projectDao = UserDatabase.getDatabase(application).projectDao()

        // assigns the DAO to a repository that handles the Dao request for each data class
        userRepository = UserRepository(userDao)
        projectRepository = ProjectRepository(projectDao)

        // reads all data from the database using the Dao
        readAllData = userRepository.readAllData
        readAllProjects =  projectRepository.readAllData
    }

    //A coroutine function that does not run on the main thread that enables the Viewmodel
    // to call the Dao function in the repository
    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.addUser(user)
        }
    }

    fun addProject (project: Project){
        viewModelScope.launch(Dispatchers.IO) {
            projectRepository.addProject(project)
        }
    }

}