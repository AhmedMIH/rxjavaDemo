package com.example.rxjava.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rxjava.db.Repo
import com.example.rxjava.network.GithubApiClient
import com.example.rxjava.repository.RepoLocalSource
import com.example.rxjava.repository.RepoRemoteSource
import com.example.rxjava.repository.RepoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RepoViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val repoLiveData = MutableLiveData<List<Repo>>()

    val repository = RepoRepository(RepoRemoteSource, RepoLocalSource)

    fun getMyStartsRepos(username: String) {
        if (repoLiveData.value !=null){
            return
        }
        val repoDisposable:Disposable = repository.fetchRepos(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                repoLiveData.value = it
            }
        compositeDisposable.add(repoDisposable)
    }

    fun getLiveData(): LiveData<List<Repo>> = repoLiveData
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}