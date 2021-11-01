package com.example.androidpractice

import android.provider.ContactsContract

data class MyProfile (
    val firstName :String,
    val lastName : String,
    val position : String,
    val profileImageLoc : String,
    val profileProjects: MutableList<Project>
        )

data class Project(
    val projectName: String,
    val projectSubHeader: String,
    val projectDescription: String
)

class Projects{

    val projectList : MutableList<Project>

    constructor() {

        this.projectList = mutableListOf<Project>(Project(
            projectName = "Counter Strike", projectSubHeader = "A better game than Valorant",
            projectDescription = "Counter-Strike: Global Offensive (CS: GO) expands upon the team-based action gameplay that it pioneered when it was launched 19 years ago. CS: GO features new maps, characters, weapons, and game modes, and delivers updated versions of the classic CS content."
        ),Project(
            projectName = "Rasta Man", projectSubHeader = "Half Human Half Zombie",
            projectDescription = "A Rastaman is a man who is running for a Senate seat to oppose China’s influence in the region. He had already been in the news all over the world for a long time because of his goal to get to stand for elections and to get elected to the Senate.\n" +
                    "\n" +
                    "Mr. Ronaldo Plaza, known as Rastaman Yow, demonstrated with banners in his hands. When he was photographed demonstrating in favor of protecting the Filipinos territory, he went viral. Let us know more about how he became viral prior to learning about Who Is Rastaman for President.\n" +
                    "\n"
        ))
    }

}

class MyProfiles {

     val profileList : MutableList<MyProfile>

    constructor(){

        profileList = mutableListOf<MyProfile>(
            MyProfile( firstName = "Alejandro", lastName = "Blando", position = "Software Engineer Trainee",
                profileImageLoc = "", profileProjects = Projects().projectList
            )
        )
    }




}