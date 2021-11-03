package com.example.androidpractice

import com.example.androidpractice.data.MyProfile
import com.example.androidpractice.data.project.Project


class Projects{

    val projectList : MutableList<Project>

    constructor() {

        this.projectList = mutableListOf<Project>(Project(
            projectName = "Save the Earth", projectSubHeader = "Stopping the Chinese Invasion",
            projectDescription = "Counter-Strike: Global Offensive (CS: GO) expands upon the team-based action gameplay that it pioneered when it was launched 19 years ago. CS: GO features new maps, characters, weapons, and game modes, and delivers updated versions of the classic CS content."
        ,projectImage  = "https://pbs.twimg.com/media/Dpml28VU4AINtQT.jpg", id = 0
        ), Project(
            projectName = "Mangekyou Sharingan", projectSubHeader = "Half Human Half Uchiha",
            projectDescription = "A Rastaman is a man who is running for a Senate seat to oppose China’s influence in the region. He had already been in the news all over the world for a long time because of his goal to get to stand for elections and to get elected to the Senate.\n" +
                    "\n" +
                    "Mr. Ronaldo Plaza, known as Rastaman Yow, demonstrated with banners in his hands. When he was photographed demonstrating in favor of protecting the Filipinos territory, he went viral. Let us know more about how he became viral prior to learning about Who Is Rastaman for President.\n" +
                    "\n" +
                    "A Rastaman is a man who is running for a Senate seat to oppose China’s influence in the region. He had already been in the news all over the world for a long time because of his goal to get to stand for elections and to get elected to the Senate.\n" +
                    "\n" +
                    "Mr. Ronaldo Plaza, known as Rastaman Yow, demonstrated with banners in his hands. When he was photographed demonstrating in favor of protecting the Filipinos territory, he went viral. Let us know more about how he became viral prior to learning about Who Is Rastaman for President.\n" +
                    "\n",
            projectImage = "https://scontent.fmnl4-3.fna.fbcdn.net/v/t1.6435-9/52536892_2256432321239937_5140111912326922240_n.jpg?_nc_cat=102&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=I7QcACA7WbgAX92yS2t&_nc_ht=scontent.fmnl4-3.fna&oh=e93c886430352a05c638b3f804db837b&oe=61A74F0B",
            id = 21
        ))

    }

}

class MyProfiles {

     val profileList : MutableList<MyProfile>

    constructor(){

        profileList = mutableListOf<MyProfile>(
            MyProfile( firstName = "Rasta", lastName = "Man", position = "Software Engineer Trainee",
                profileImageLoc = "https://www.manilachannel.com/wp-content/uploads/2021/10/2.jpg", profileProjects = Projects().projectList
            )
        )
    }




}