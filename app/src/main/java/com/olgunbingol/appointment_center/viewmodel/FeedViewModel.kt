package com.olgunbingol.appointment_center.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.olgunbingol.appointment_center.model.Country
import com.olgunbingol.appointment_center.service.CountryApiServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeedViewModel : ViewModel() {
    private val countryApiService = CountryApiServices()
    private val disposable = CompositeDisposable()
    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData() {

getDataFromApi()
    }
    private fun getDataFromApi() {
        countryLoading.value = true
        disposable.add(countryApiService.getData().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(object : DisposableSingleObserver<List<Country>>(){
            override fun onSuccess(t: List<Country>) {
                countries.value = t
                countryError.value = false
                countryLoading.value = false
            }

            override fun onError(e: Throwable) {
                countryLoading.value = false
                countryError.value = true
                e.printStackTrace()

            }

        }))

    }


}