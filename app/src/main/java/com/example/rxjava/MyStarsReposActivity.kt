package com.example.rxjava

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rxjava.adapter.GithubRepoAdapter
import com.example.rxjava.viewmodel.RepoViewModel
import kotlinx.android.synthetic.main.activity_my_stars_repos.*


class MyStarsReposActivity : AppCompatActivity() {

    private lateinit var repoAdapter : GithubRepoAdapter
    private lateinit var repoViewModel: RepoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_stars_repos)

        repoViewModel = ViewModelProvider(this).get(RepoViewModel::class.java)

        MyStarList.layoutManager = LinearLayoutManager(application)
        val divider = DividerItemDecoration(MyStarList.context,DividerItemDecoration.VERTICAL)
        repoAdapter = GithubRepoAdapter()
        MyStarList.adapter = repoAdapter
        MyStarList.addItemDecoration(divider)

        getStartRepos(repoViewModel)
        observeMyStars(repoViewModel)

    }

    private fun getStartRepos(repoViewModel: RepoViewModel) {
        repoViewModel.getMyStartsRepos("mrabelwahed")

    }
    fun observeMyStars(repoViewModel: RepoViewModel) {
        repoViewModel.getLiveData().observe(this, Observer {
            repoAdapter.addRepos(ArrayList(it))
        })
    }
}