package com.example.androidpractice.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.androidpractice.R
import com.example.androidpractice.data.project.Project
import com.example.androidpractice.data.user.User
import com.example.androidpractice.databinding.FragmentAddProfileBinding
import com.example.androidpractice.viewmodels.MyViewModel
import com.example.androidpractice.viewmodels.ProjectViewModel


class AddProfileFragment : Fragment() {

    lateinit var binding: FragmentAddProfileBinding
    lateinit var myProjectViewModel: ProjectViewModel
    lateinit var myViewModel: MyViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)
        activity?.run {
            myViewModel = ViewModelProviders.of(this)[MyViewModel::class.java]
        } ?: throw Throwable("invalid activity")
        myViewModel.updateActionBarTitle("Add Project")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentAddProfileBinding.inflate(layoutInflater)

        binding.addButton.setOnClickListener{
            insertDataToDatabase()
        }
        myProjectViewModel = ViewModelProvider(this)[ProjectViewModel::class.java]
        myViewModel = ViewModelProvider(this)[MyViewModel::class.java]


        return binding.root
    }



    private fun insertDataToDatabase() {
        val projectName = binding.projectNameEt.text.toString()
        val projectHeader = binding.subHeaderEt.text.toString()
        val imageURL = binding.imageURLEt.text.toString()
        val projectDescription = binding.descriptionEt.text.toString()

        if (inputCheck(projectName,projectHeader,imageURL,projectDescription)){
            //Create User Object
            val user = User(id = 0,firstName = projectName, lastName = projectHeader,
                position = projectDescription,profileImageLoc = imageURL ) //change this to Projects DAO , Using User data as example through walkthrough

            val project = Project(id = 0, projectImage = imageURL,projectName = projectName,projectSubHeader = projectHeader,
                projectDescription = projectDescription ) //change this to Projects DAO , Using User data as example through walkthrough

            // Add data to Database
              myProjectViewModel.addUser(user)
              myProjectViewModel.addProject(project)
            Toast.makeText(requireContext(), "Added ${projectName} Successfully!",Toast.LENGTH_LONG).show()
            // Go back to ListFragment
            findNavController().navigate(R.id.action_addProfile_Fragment_to_projectList_fragment)

        }
    }

    private fun inputCheck( projectName : String, projectHeader: String, imageURL : String, projectDescription: String): Boolean { // checks if the EditText are all not empty
        return !(TextUtils.isEmpty(projectName) && TextUtils.isEmpty(projectHeader) && TextUtils.isEmpty(projectDescription)
                && TextUtils.isEmpty(imageURL))

    }


}