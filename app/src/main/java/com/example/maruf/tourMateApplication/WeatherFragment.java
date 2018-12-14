package com.example.maruf.tourMateApplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;


public class WeatherFragment extends Fragment {
    private ImageView iv;
    private boolean value;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        iv = view.findViewById(R.id.inCelceusIv);
        value = false;
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(value){
                    iv.setImageResource(R.drawable.thermometer);
                    Toasty.normal(getActivity(),"Temperature Changed to celsius",Toast.LENGTH_LONG).show();
                    value = false;
                }else{
                    iv.setImageResource(R.drawable.farenheit);
                    Toasty.normal(getActivity(),"Temperature Changed to fahrenheit",Toast.LENGTH_LONG).show();
                    value = true;
                }
            }
        });
        return view;


    }
}
