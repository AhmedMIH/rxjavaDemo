package com.example.rxjava.repository

import com.example.rxjava.db.Repo
import io.reactivex.Observable

class RepoRepository(val repoRemoteSource: RepoRemoteSource, val repoLocalSource: RepoLocalSource) :
    RepoDataSource {
    override fun fetchRepos(username: String): Observable<List<Repo>> {
        return Observable.concat(
            repoLocalSource.fetchRepos(username),
            repoRemoteSource.fetchRepos(username)
                .doOnNext{saveRepos(it)}
                .onErrorResumeNext(Observable.empty())
        )
    }

    override fun saveRepos(repos: List<Repo>) {
       repoLocalSource.saveRepos(repos)
    }
}