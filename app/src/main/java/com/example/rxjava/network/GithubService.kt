package com.example.rxjava.network

import com.example.rxjava.db.Repo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("users/{user}/starred")
    fun getStarredRepos(@Path("user") username: String): Observable<List<Repo>>
}