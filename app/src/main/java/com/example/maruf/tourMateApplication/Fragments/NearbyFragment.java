package com.example.maruf.tourMateApplication.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.maruf.tourMateApplication.Adapter.NearbyAdapter;
import com.example.maruf.tourMateApplication.ApiClient;
import com.example.maruf.tourMateApplication.ApiService;
import com.example.maruf.tourMateApplication.NearbyResponse.NearbyResponse;
import com.example.maruf.tourMateApplication.NearbyResponse.Result;
import com.example.maruf.tourMateApplication.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NearbyFragment extends Fragment {

    private ApiService service;
    private double lat = 23.76;
    private double lon = 90.38;
    private int radius =500;
    private String place_type = "restaurant";
    private RecyclerView recyclerView;
    private NearbyAdapter nearbyAdapter;
    List<Result> results;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nearby, container, false);
        recyclerView = view.findViewById(R.id.nearbyId);
        service = ApiClient.getRetrofit().create(ApiService.class);
        String url = String.format("place/nearbysearch/json?location=%f,%f&radius=%d&type=%s&key=%s",lat,lon,radius,place_type,getResources().getString(R.string.apiKey));
        Call<NearbyResponse> nearbyResponseCall = service.getNearby(url);
        nearbyResponseCall.enqueue(new Callback<NearbyResponse>() {
            @Override
            public void onResponse(Call<NearbyResponse> call, Response<NearbyResponse> response) {
                if(response.code() == 200){
                    NearbyResponse nearbyResponse =  response.body();
                    results = nearbyResponse.getResults();
                    nearbyAdapter = new NearbyAdapter(results,getActivity());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(nearbyAdapter);

                    }
            }

            @Override
            public void onFailure(Call<NearbyResponse> call, Throwable t) {

            }
        });

        return view ;
    }


}
