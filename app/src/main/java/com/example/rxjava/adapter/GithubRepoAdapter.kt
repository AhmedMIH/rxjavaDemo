package com.example.rxjava.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rxjava.R
import com.example.rxjava.db.Repo
import kotlinx.android.synthetic.main.stars_item.view.*

class GithubRepoAdapter() : RecyclerView.Adapter<StarRepoViewHolder>() {
    val data = ArrayList<Repo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarRepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.stars_item, parent, false)
        return StarRepoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: StarRepoViewHolder, position: Int) {
        holder.repoName.text = data[position].name
        holder.repoLang.text = data[position].Lang
        data[position].desc?.let {
            holder.repoDesc.text = data[position].desc
        }?:run {
            holder.repoDesc.text = "No desc"
        }
        holder.repoStarCount.text = data[position].starCount.toString()
    }

    fun addRepos(repos:ArrayList<Repo>){
        data.addAll(repos)
        notifyDataSetChanged()
    }

}

class StarRepoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val repoName = view.repoName
    val repoDesc = view.desc
    val repoLang = view.lang
    val repoStarCount = view.starsCount

}