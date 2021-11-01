package com.example.androidpractice

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.fragment.findNavController
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidpractice.databinding.ProjectItemBinding
import com.example.androidpractice.fragments.ProjectView_fragment

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

        holder.itemView.setOnClickListener{

            var intent = Intent(context,MainActivity::class.java).apply {

            }
            context.startActivity(intent)
        }

    }


    override fun getItemCount(): Int {
        return data.size
    }
}