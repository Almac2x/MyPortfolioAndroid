package com.example.androidpractice.fragments

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.androidpractice.R
import com.example.androidpractice.databinding.ActivityMainBinding
import com.example.androidpractice.databinding.ProjectViewFragmentLayoutBinding

class ProjectView_fragment : Fragment() {

    lateinit var binding: ProjectViewFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ProjectViewFragmentLayoutBinding.inflate(layoutInflater)

        binding.descriptionTextView.movementMethod = ScrollingMovementMethod()

        val url = arguments?.get("projectImage")

        Glide.with(this).load(url).into(binding.projectImageBigView)

        binding.descriptionTextView.text = arguments?.get("projectDesc").toString()

        println( arguments?.get("FName").toString())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // binding = ProjectViewFragmentLayoutBinding.inflate(layoutInflater)




    }


}