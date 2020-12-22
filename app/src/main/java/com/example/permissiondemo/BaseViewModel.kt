package com.example.permissiondemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    var bag: CompositeDisposable = CompositeDisposable()
    val loadingDataLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val noInternetLiveData: MutableLiveData<Boolean> = MutableLiveData()


    fun getLoadingLiveData() = loadingDataLiveData

    override fun onCleared() {
        super.onCleared()
        bag.dispose()
    }
}
