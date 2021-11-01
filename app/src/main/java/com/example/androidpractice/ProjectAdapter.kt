package com.example.androidpractice

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidpractice.databinding.ProjectItemBinding

class ProjectAdapter(val context: Context, private val data: MutableList<Project>) : RecyclerView.Adapter<ProjectAdapter.MyViewHolder>() {

     lateinit var binding: ProjectItemBinding



    inner class MyViewHolder (val binding: ProjectItemBinding) :RecyclerView.ViewHolder(binding.root) {


        fun bindProjectInfo(name: String, header:String) {
          binding.projectTextName.text= name
            binding.projectTextDescription.text = header
        }

        fun bindImage(url: String?) {
            if (url != null) {
                Glide.with(context).load(url).into(binding.projectImageView)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ProjectItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindProjectInfo(name = data[position].projectName, header = data[position].projectSubHeader)
        holder.bindImage(url = data[position].projectImage)

        holder.itemView.setOnClickListener(){
            Toast.makeText(holder.itemView.context, data[position].projectName,
                Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}