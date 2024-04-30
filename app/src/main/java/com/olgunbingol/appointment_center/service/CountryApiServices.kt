package com.olgunbingol.appointment_center.service

import com.olgunbingol.appointment_center.model.Country
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountryApiServices {
    //https://raw.githubusercontent.com/olgunpros/SWIFT-API-Oil-Prices-App/main/fSome%2B-%2BAny%2BOdevi%202.json
    private val BASE_URL = "https://raw.githubusercontent.com/"
    private val api =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
            .create(CountryApi::class.java)

    fun getData(): Single<List<Country>> {
        return api.getCountries()
    }

}
