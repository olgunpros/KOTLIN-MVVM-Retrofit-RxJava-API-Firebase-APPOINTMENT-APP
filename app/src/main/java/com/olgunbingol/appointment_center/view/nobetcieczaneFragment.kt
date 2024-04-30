package com.olgunbingol.appointment_center.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.olgunbingol.appointment_center.R
import com.olgunbingol.appointment_center.adapter.CountryAdapter
import com.olgunbingol.appointment_center.viewmodel.FeedViewModel

class nobetcieczaneFragment : Fragment() {
    private lateinit var viewModel: FeedViewModel
    private lateinit var countryAdapter: CountryAdapter
    private lateinit var countryList: RecyclerView
    private lateinit var countryError: View
    private lateinit var countryLoading: View
    private lateinit var mapView : MapView
    private lateinit var mMap: GoogleMap





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nobetcieczane, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val a = String

        countryList = view.findViewById(R.id.countryList)
        countryError = view.findViewById(R.id.countryError)
        countryLoading = view.findViewById(R.id.countryLoading)

        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        countryAdapter = CountryAdapter(arrayListOf())
        countryList.layoutManager = LinearLayoutManager(requireContext())
        countryList.adapter = countryAdapter



        observeLiveData()
    }
     fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
    fun showCountryOnMap(position : Int) {
        val (latitude, longitude) = countryAdapter.getlongitude(position )
        //mapview
    }

    private fun observeLiveData() {
        viewModel.countries.observe(viewLifecycleOwner, Observer { countries ->
            countries?.let {
                countryList.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }
        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                countryError.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                countryLoading.visibility = if (it) View.VISIBLE else View.GONE
                countryList.visibility = if (it) View.GONE else View.VISIBLE
                countryError.visibility = View.GONE
            }
        })
    }
}
