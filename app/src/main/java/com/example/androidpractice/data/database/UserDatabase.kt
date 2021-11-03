package com.example.androidpractice.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidpractice.data.project.Project
import com.example.androidpractice.data.project.ProjectDao
import com.example.androidpractice.data.user.User
import com.example.androidpractice.data.user.UserDao

@Database(entities = [User::class, Project::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {

   abstract fun userDao(): UserDao
   abstract fun projectDao() : ProjectDao

   companion object{
       @Volatile
       private var INSTANCE: UserDatabase? = null

       fun getDatabase(context:Context): UserDatabase {
           val tempInstance = INSTANCE
           if (tempInstance != null){
               return tempInstance
           }
           synchronized(this){
               val instance = Room.databaseBuilder(
                   context.applicationContext,
                   UserDatabase::class.java,
                   "user_database"
               ).build()
               INSTANCE = instance
               return instance

           }
       }
   }

}