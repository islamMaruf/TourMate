package com.example.maruf.tourMateApplication;

import com.example.maruf.tourMateApplication.NearbyResponse.NearbyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiService {
    @GET
    Call<NearbyResponse> getNearby(@Url String url);
}
