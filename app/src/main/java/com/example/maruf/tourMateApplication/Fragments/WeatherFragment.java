package com.example.maruf.tourMateApplication.Fragments;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maruf.tourMateApplication.ApiClient;
import com.example.maruf.tourMateApplication.ApiService;
import com.example.maruf.tourMateApplication.R;
import com.example.maruf.tourMateApplication.WeatherResponse.WeatherResponse;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;




public class WeatherFragment extends Fragment {
    public WeatherFragment() {
    }

    private ApiService service;
    private TextView textView;
    private String unit = "metric";
    private int lat = 23;
    private int lon =90;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_weather, container, false);
        textView = view.findViewById(R.id.dateId);
        String url = String.format("weather?lat=%d&lon=%d&units=%s&appid=%s",lat,lon,unit,getResources().getString(R.string.weatherApiKey));
        service = ApiClient.getCurrentWeather().create(ApiService.class);
        Call<WeatherResponse> userResponseCall = service.getWeatherDetail(url);
        userResponseCall.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.code() == 200) {
                    WeatherResponse weatherResponse = response.body();
                    textView.setText(weatherResponse.getDt().toString());
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });



        return view;


    }
}
