package com.example.androidpractice

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidpractice.data.project.Project
import com.example.androidpractice.databinding.ProjectItemBinding
import com.example.androidpractice.fragments.EditProjectFragmentDirections
import com.example.androidpractice.fragments.ProjectListFragmentDirections
import com.example.androidpractice.viewmodels.ProjectViewModel

class ProjectAdapter(val context: Context, var clicker: OnItemClickListener,val viewModelProvider : ProjectViewModel) : RecyclerView.Adapter<ProjectAdapter.MyViewHolder>() {

    lateinit var binding: ProjectItemBinding
    private var projectList: List<Project> = emptyList<Project>()
    private lateinit var projectViewModel: ProjectViewModel

    inner class MyViewHolder (val binding: ProjectItemBinding, clickListener: OnItemClickListener) :RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        var itemClickListener : OnItemClickListener = clickListener

        init {
            itemView.setOnClickListener(this)
        }

        fun bindProjectInfo(name: String, header:String) {
          binding.projectTextName.text= name
          binding.projectTextDescription.text = header
        }

        fun bindImage(url: String?) {

            url?.let { imageUrl ->
                Glide.with(context).load(imageUrl).into(binding.projectImageView)
            }

        }

        override fun onClick(p0: View?) {
            itemClickListener.onItemClickListener(adapterPosition)
        }


    }

    interface OnItemClickListener{

        fun onItemClickListener (positon: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ProjectItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        this.projectViewModel = viewModelProvider

        return MyViewHolder(binding,clicker)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem  = projectList[position]

        holder.bindProjectInfo(name = currentItem.projectName,
            header = currentItem.projectSubHeader)

        holder.bindImage(url = currentItem?.projectImage)

        holder.binding.card.setOnLongClickListener {

            val alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle("Project: ${currentItem.projectName}")
            alertDialog.setMessage("What do you want to do with: \n ${currentItem.projectName}")
            alertDialog.setPositiveButton("Delete"){_,_ ->

                projectViewModel.deleteProject(currentItem)
                Toast.makeText(context, "Removed project ${currentItem.projectName}",Toast.LENGTH_SHORT).show()

            }
            alertDialog.setNegativeButton("Edit"){_,_->
                val action = ProjectListFragmentDirections.actionProjectListFragmentToEditProjectFragment(currentItem)
                holder.itemView.findNavController().navigate(action)
            }

            alertDialog.create().show()

            true
        }

    }

    override fun getItemCount(): Int {
        return projectList.size
    }

    fun setData(project : List<Project>){
        this.projectList = project
        notifyDataSetChanged()
    }

    fun itemOptions(){

    }


}