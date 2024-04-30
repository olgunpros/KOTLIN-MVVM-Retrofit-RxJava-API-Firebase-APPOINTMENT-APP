package com.olgunbingol.appointment_center.service

import com.olgunbingol.appointment_center.model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface CountryApi {
    //https://raw.githubusercontent.com/olgunpros/SWIFT-API-Oil-Prices-App/main/kfSome%2B-%2BAny%2BOdevi%202.json
    //url curl --location 'https://www.nosyapi.com/apiv2/service/pharmacies-on-duty/locations?latitude=38.432561&longitude=27.143503&apiKey=APIKEY'
    @GET("olgunpros/SWIFT-API-Oil-Prices-App/main/adad.json")
    fun getCountries():Single<List<Country>>
}