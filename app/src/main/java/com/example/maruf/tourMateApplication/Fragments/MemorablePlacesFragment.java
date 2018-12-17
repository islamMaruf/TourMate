package com.example.maruf.tourMateApplication.Fragments;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.maruf.tourMateApplication.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import es.dmoral.toasty.Toasty;


public class MemorablePlacesFragment extends Fragment {
    private com.github.clans.fab.FloatingActionButton clickCamera;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_memorable_places, container, false);
        clickCamera = view.findViewById(R.id.clickCamera);
        clickCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(data,1);
            }
        });
        return view;
    }


}

