package com.example.androidpractice.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.bumptech.glide.Glide
import com.example.androidpractice.MyProfiles
import com.example.androidpractice.ProjectAdapter
import com.example.androidpractice.R
import com.example.androidpractice.databinding.ActivityMainBinding
import com.example.androidpractice.databinding.ProjectslistFragmentLayoutBinding
import com.example.androidpractice.viewmodels.MyViewModel


class projectList_fragment : Fragment(R.layout.projectslist_fragment_layout) ,  ProjectAdapter.OnItemClickListener{

    lateinit var binding: ProjectslistFragmentLayoutBinding
    lateinit var myViewModel : MyViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.run {
            myViewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        } ?: throw Throwable("invalid activity")
        myViewModel.updateActionBarTitle("MyPortfolio")


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        binding = ProjectslistFragmentLayoutBinding.bind(view)

        val profileName = "${ myViewModel.myProfiles.profileList[0].firstName} ${ myViewModel.myProfiles.profileList[0].lastName}"

        binding.profileNameView.text = "${profileName}"
        binding.profilePositionView.text = "${ myViewModel.myProfiles.profileList[0].position}"

        Glide.with(view).load(myViewModel.myProfiles.profileList[0].profileImageLoc).into(binding.profilePic)

        val rcv = binding.projectListRecycleViewer
        rcv?.adapter = ProjectAdapter(requireContext(),myViewModel.myProfiles.profileList[0].profileProjects,this)
        
        val divider = DividerItemDecoration(this.context,DividerItemDecoration.VERTICAL)
        ResourcesCompat.getDrawable(resources,R.drawable.divider,null)?.let { divider.setDrawable(it) }

        rcv.addItemDecoration(divider)

    }

    override fun onItemClickListener(positon: Int) {
        var bundle = Bundle()

        val projectName = "${myViewModel.myProfiles.profileList[0].profileProjects[positon].projectName}"
        val projectDescription = "${myViewModel.myProfiles.profileList[0].profileProjects[positon].projectDescription}"
        val projectImage = "${myViewModel.myProfiles.profileList[0].profileProjects[positon].projectImage}"

        bundle.putString("projectName",projectName)
        bundle.putString("projectDesc",projectDescription)
        bundle.putString("projectImage",projectImage)

        findNavController().navigate(R.id.action_projectList_fragment_to_projectView_fragment, bundle)
    }


}