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
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidpractice.databinding.ProjectItemBinding
import com.example.androidpractice.fragments.ProjectView_fragment
import com.example.androidpractice.fragments.projectList_fragment

class ProjectAdapter(val context: Context, private val data: MutableList<Project>, var clicker: OnItemClickListener) : RecyclerView.Adapter<ProjectAdapter.MyViewHolder>() {

     lateinit var binding: ProjectItemBinding

    inner class MyViewHolder (val binding: ProjectItemBinding, clickListener: OnItemClickListener) :RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        lateinit var itemClickListener : OnItemClickListener

        init {
            this.itemClickListener = clickListener
            itemView.setOnClickListener(this)
        }

        fun bindProjectInfo(name: String, header:String) {
          binding.projectTextName.text= name
            binding.projectTextDescription.text = header
        }

        fun bindImage(url: String?) {
            if (url != null) {
                Glide.with(context).load(url).into(binding.projectImageView)
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
        return MyViewHolder(binding,clicker)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindProjectInfo(name = data[position].projectName, header = data[position].projectSubHeader)
        holder.bindImage(url = data[position].projectImage)


       /* holder.itemView.setOnClickListener ( object : View.OnClickListener {
            override fun onClick(v: View?) {

                val activity = v?.context as AppCompatActivity
                val fragment = ProjectView_fragment()
                activity.supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment_container,fragment).addToBackStack(null).commit()

            }
        })*/

    }


    override fun getItemCount(): Int {
        return data.size
    }
}