package com.example.rxjava.repository

import com.example.rxjava.RxApp
import com.example.rxjava.db.AppDatabase
import com.example.rxjava.db.Repo
import io.reactivex.Observable

object RepoLocalSource :RepoDataSource {
    override fun fetchRepos(username: String): Observable<List<Repo>> {
        return Observable.fromCallable {
            AppDatabase.getInstance(RxApp.INSTANCE)?.getRepoDAO()!!.fetchAllMyStarsRepos()
        }
    }

    override fun saveRepos(repos: List<Repo>) {
        AppDatabase.getInstance(RxApp.INSTANCE)?.getRepoDAO()!!.saveAllMyStarsRepo(repos)

    }
}