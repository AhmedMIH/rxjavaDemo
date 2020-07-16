package com.example.rxjava.repository

import com.example.rxjava.db.Repo
import io.reactivex.Observable
import kotlin.collections.ArrayList

interface RepoDataSource {
    fun fetchRepos(username:String) : Observable<List<Repo>>
    fun saveRepos(repos : List<Repo> )
}