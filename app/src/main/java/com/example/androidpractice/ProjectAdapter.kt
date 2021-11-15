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

class ProjectAdapter(val context: Context, var clicker: OnItemClickListener,var clickerLong: OnItemLongClickListener) : RecyclerView.Adapter<ProjectAdapter.MyViewHolder>() {

    lateinit var binding: ProjectItemBinding
    private var projectList: List<Project> = emptyList<Project>()

    inner class MyViewHolder (val binding: ProjectItemBinding, clickListener: OnItemClickListener, longClick : OnItemLongClickListener) :RecyclerView.ViewHolder(binding.root), View.OnClickListener,View.OnLongClickListener {

        var itemClickListener : OnItemClickListener = clickListener
        var itemLongClickListener : OnItemLongClickListener = longClick

        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
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

        override fun onLongClick(p0: View?): Boolean {

            itemLongClickListener.onItemLongClickListener((adapterPosition))
            return true
        }


    }

    interface OnItemClickListener{
        fun onItemClickListener (positon: Int)
    }

    interface OnItemLongClickListener{
        fun onItemLongClickListener (positon: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
         binding = ProjectItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding,clicker,clickerLong)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem  = projectList[position]

        holder.bindProjectInfo(name = currentItem.projectName,
            header = currentItem.projectSubHeader)

        holder.bindImage(url = currentItem.projectImage)


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