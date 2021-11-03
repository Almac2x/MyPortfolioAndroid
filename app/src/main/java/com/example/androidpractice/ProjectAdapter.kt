package com.example.androidpractice

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidpractice.data.project.Project
import com.example.androidpractice.databinding.ProjectItemBinding

class ProjectAdapter(val context: Context, var clicker: OnItemClickListener) : RecyclerView.Adapter<ProjectAdapter.MyViewHolder>() {

     lateinit var binding: ProjectItemBinding
     private var projectList: List<Project> = emptyList<Project>()

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

            url?.let { imageUrl ->
                Glide.with(context).load(imageUrl).into(binding.projectImageView)
            }

          /*  if (url != null) {
                Glide.with(context).load(url).into(binding.projectImageView)
            }*/
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
        holder.bindProjectInfo(name = projectList[position].projectName.toString(),
            header = projectList[position].projectSubHeader.toString())

        holder.bindImage(url = projectList[position]?.projectImage.toString())

        holder.binding.card.setOnLongClickListener {
            Toast.makeText(context, "Long click detected", Toast.LENGTH_SHORT).show()
            true
        }


          // this creates a new activity when an Item in the RecycleView gets click
       /* holder.itemView.setOnClickListener ( object : View.OnClickListener {
            override fun onClick(v: View?) {

                val activity = v?.context as AppCompatActivity
                val fragment = ProjectView_fragment()
                activity.supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment_container,fragment).addToBackStack(null).commit()

            }
        })*/

    }

    override fun getItemCount(): Int {
        return projectList.size
    }

    fun setData(project : List<Project>){
        this.projectList = project
        notifyDataSetChanged()
    }
}