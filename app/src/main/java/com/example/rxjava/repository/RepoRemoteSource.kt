package com.example.rxjava.repository

import com.example.rxjava.db.Repo
import com.example.rxjava.network.GithubApiClient
import io.reactivex.Observable

object RepoRemoteSource : RepoDataSource {
    override fun fetchRepos(username: String): Observable<List<Repo>> {

        return GithubApiClient.githubService().getStarredRepos(username)
    }

    override fun saveRepos(repos: List<Repo>) {

    }
}