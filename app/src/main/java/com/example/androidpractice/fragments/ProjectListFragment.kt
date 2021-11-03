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
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.bumptech.glide.Glide
import com.example.androidpractice.DividerItemDecorator
import com.example.androidpractice.MyProfiles
import com.example.androidpractice.ProjectAdapter
import com.example.androidpractice.R
import com.example.androidpractice.data.project.Project
import com.example.androidpractice.databinding.ActivityMainBinding
import com.example.androidpractice.databinding.ProjectslistFragmentLayoutBinding
import com.example.androidpractice.viewmodels.MyViewModel
import com.example.androidpractice.viewmodels.UserViewModel


class ProjectListFragment : Fragment(R.layout.projectslist_fragment_layout) ,  ProjectAdapter.OnItemClickListener{

    lateinit var binding: ProjectslistFragmentLayoutBinding
    lateinit var myViewModel : MyViewModel
    lateinit var userViewModel: UserViewModel


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.run {
            myViewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        } ?: throw Throwable("invalid activity")
        myViewModel.updateActionBarTitle("MyPortfolio")

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Initialize bind
        binding = ProjectslistFragmentLayoutBinding.bind(view)

        //RecycleView implementations
        val rcv = binding.projectListRecycleViewer
        //rcv?.adapter = ProjectAdapter(requireContext(),myViewModel.myProfiles.profileList[0].profileProjects,this)
        val adapter = ProjectAdapter( clicker = this,context = requireContext())
        rcv?.adapter = adapter

        //Initialize ViewModel in this Fragment
        myViewModel = ViewModelProvider(this)[MyViewModel::class.java] // remove this after userViewModel is setup properly
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        userViewModel.readAllProjects.observe(viewLifecycleOwner, Observer { project ->
            adapter.setData(project)
        })



        val profileName = "${ myViewModel.myProfiles.profileList[0].firstName} ${ myViewModel.myProfiles.profileList[0].lastName}"

        binding.profileNameView.text = "${profileName}"
        binding.profilePositionView.text = "${ myViewModel.myProfiles.profileList[0].position}"

        // initialize here the Profile Pic at Fragment Starup
        Glide.with(view).load(myViewModel.myProfiles.profileList[0].profileImageLoc).into(binding.profilePic)



        //val divider = DividerItemDecoration(this.context,DividerItemDecoration.VERTICAL)
       // ResourcesCompat.getDrawable(resources,R.drawable.divider,null)?.let { divider.setDrawable(it) }

        //added a Divider Decoration after each item
        val divider = DividerItemDecorator(ContextCompat.getDrawable(requireContext(), R.drawable.divider)!!) // uses a custom item decorator where the last item does not show a divider
        rcv.addItemDecoration(divider)

        //Navigates to the add Project Fragment
        binding.floatingActionBar.setOnClickListener(){
            findNavController().navigate(R.id.action_projectList_fragment_to_addProfile_Fragment)
        }



    }

    override fun onItemClickListener(positon: Int) {
        var bundle = Bundle()

        val project = userViewModel.readAllProjects.value?.get(positon)
        println( "Project Name: "+ project?.projectName)

        val projectName = "${project?.projectName}"
        val projectDescription = "${project?.projectDescription}"
        val projectImage = "${project?.projectImage}"

        bundle.putString("projectName",projectName)
        bundle.putString("projectDesc",projectDescription)
        bundle.putString("projectImage",projectImage)

        findNavController().navigate(R.id.action_projectList_fragment_to_projectView_fragment, bundle)
    }


}