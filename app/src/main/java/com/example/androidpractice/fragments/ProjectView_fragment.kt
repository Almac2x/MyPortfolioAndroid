package com.example.androidpractice.fragments

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.example.androidpractice.R
import com.example.androidpractice.databinding.ActivityMainBinding
import com.example.androidpractice.databinding.ProjectViewFragmentLayoutBinding
import com.example.androidpractice.viewmodels.MyViewModel

class ProjectView_fragment : Fragment() {

    lateinit var binding: ProjectViewFragmentLayoutBinding
    lateinit var myViewModel: MyViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.run {
            myViewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        } ?: throw Throwable("invalid activity")
        myViewModel.updateActionBarTitle(arguments?.get("projectName").toString())

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ProjectViewFragmentLayoutBinding.inflate(layoutInflater)

        myViewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)

        binding.descriptionTextView.movementMethod = ScrollingMovementMethod()

        val url = arguments?.get("projectImage")


        Glide.with(this).load(url).into(binding.projectImageBigView)

        binding.descriptionTextView.text = arguments?.get("projectDesc").toString()

        println( arguments?.get("projectName").toString())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // binding = ProjectViewFragmentLayoutBinding.inflate(layoutInflater)




    }


}