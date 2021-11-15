package com.example.androidpractice.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.androidpractice.R
import com.example.androidpractice.data.project.Project
import com.example.androidpractice.databinding.FragmentEditProjectBinding
import com.example.androidpractice.viewmodels.MyViewModel
import com.example.androidpractice.viewmodels.ProjectViewModel


class EditProjectFragment : Fragment() {

    private val args by navArgs<EditProjectFragmentArgs>()
    lateinit var  binding: FragmentEditProjectBinding
    val projectViewModel: ProjectViewModel by activityViewModels()
    val myViewModel : MyViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        setHasOptionsMenu(true)

        // Add this to all fragment
        val actionBarTitle = (activity as AppCompatActivity).supportActionBar
        actionBarTitle?.title = args.currentProject.projectName

        // Inflate the layout for this fragment

        binding = FragmentEditProjectBinding.inflate(layoutInflater)

        //gets the currentProject from Parceable args  initiated in the navigation graph
        binding.projectNameUpdateEt.setText(args.currentProject.projectName)
        binding.descriptionEt.setText(args.currentProject.projectDescription)
        binding.subHeaderUpdateEt.setText(args.currentProject.projectSubHeader)
        binding.imageURLUpdateEt.setText(args.currentProject.projectImage)

        binding.editButton.setOnClickListener{
            updateItem()
        }

        return binding.root
    }

    private fun updateItem(){
        val projectName = binding.projectNameUpdateEt.text.toString()
        val projectHeader = binding.subHeaderUpdateEt.text.toString()
        val imageURL = binding.imageURLUpdateEt.text.toString()
        val projectDescription = binding.descriptionEt.text.toString()

        if (inputCheck(projectName,projectHeader,imageURL,projectDescription)){

            // Create Project Object again

            val updatedProject = Project(projectName = projectName, projectSubHeader = projectHeader, projectImage = imageURL
                                         ,projectDescription = projectDescription, id = args.currentProject.id)

            // Update Project
            projectViewModel.updateProject(updatedProject)
            findNavController().navigate(R.id.action_editProjectFragment_to_projectList_fragment)

            Toast.makeText(context,"Updated Successfully", Toast.LENGTH_LONG ).show()
        }else{
            Toast.makeText(context,"No empty fields allowed!", Toast.LENGTH_SHORT ).show()
        }

    }

    private fun inputCheck( projectName : String, projectHeader: String, imageURL : String, projectDescription: String): Boolean { // checks if the EditText are all not empty
        return !(TextUtils.isEmpty(projectName) && TextUtils.isEmpty(projectHeader) && TextUtils.isEmpty(projectDescription)
                && TextUtils.isEmpty(imageURL))

    }



}