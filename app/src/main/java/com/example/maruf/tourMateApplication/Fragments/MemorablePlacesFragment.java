package com.example.maruf.tourMateApplication.Fragments;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
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

import java.io.ByteArrayOutputStream;


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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try{
            if (requestCode == 1 && resultCode == Activity.RESULT_OK){
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                byte[] uri = byteArrayOutputStream.toByteArray();

                final StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("MemorablePhotos").child("photos_"+System.currentTimeMillis());
                storageReference.putBytes(uri);

                Toast.makeText(getActivity(), "picture add", Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){
            Toasty.error(this.getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }



}

