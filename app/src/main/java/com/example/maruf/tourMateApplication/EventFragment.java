package com.example.maruf.tourMateApplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;


public class EventFragment extends Fragment {

    private FloatingActionButton openBottomsheetBtn;
    private EditText eventNameEt,fromDateEt,toDateEt,esatimateBudgetEt;
    private Button addEventEt;
    private FirebaseAuth firebaseAuth;
    private String userId;
    BottomSheetDialog bottomSheetDialog;
    private EventAdapter eventAdapter;
    private  List<EventCreate>eventList;
    private RecyclerView recyclerView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        eventList = new ArrayList<>();
        firebaseAuth = FirebaseAuth.getInstance();
        userId = firebaseAuth.getCurrentUser().getUid();
        recyclerView = view.findViewById(R.id.recyclerViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        loadEventListFromDatabase();
        eventAdapter = new EventAdapter(getActivity(),eventList);
        recyclerView.setAdapter(eventAdapter);

        openBottomsheetBtn = view.findViewById(R.id.openBottomSheet);
        openBottomsheetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog = new BottomSheetDialog(getActivity());
                View sheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_layout,null);
                bottomSheetDialog.setContentView(sheetView);
                bottomSheetDialog.show();
                eventNameEt = sheetView.findViewById(R.id.eventET);
                fromDateEt = sheetView.findViewById(R.id.fromDateEt);
                toDateEt = sheetView.findViewById(R.id.toDateEt);
                esatimateBudgetEt = sheetView.findViewById(R.id.estimatebudgetEt);
                addEventEt = sheetView.findViewById(R.id.addEvent);
                addEventEt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        validationAndSend();
                    }
                });
            }
        });









        return view;
    }

    private void loadEventListFromDatabase() {
        DatabaseRef.userRef.child(userId).child("Events").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                EventCreate eventCreate = dataSnapshot.getValue(EventCreate.class);
                eventList.add(eventCreate);
                eventAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void validationAndSend() {
        String eventName = eventNameEt.getText().toString();
        String fromDate = fromDateEt.getText().toString();
        String toDate = toDateEt.getText().toString();
        String budget = esatimateBudgetEt.getText().toString();
        if(!eventName.isEmpty() || !fromDate.isEmpty() || !toDate.isEmpty() || !budget.isEmpty()){
            saveEventToDatabase(eventName,fromDate,toDate,budget);
        }else{
            Toasty.warning(getActivity(),"Please fill all the field",Toast.LENGTH_SHORT,false).show();
        }
    }

    private void saveEventToDatabase(String eventName,String fromDate,String toDate,String budget) {
        EventCreate eventDetails = new EventCreate(eventName,fromDate,toDate,budget);
        firebaseAuth = FirebaseAuth.getInstance();
        userId = firebaseAuth.getCurrentUser().getUid();
        DatabaseRef.userRef.child(userId).child("Events").push().setValue(eventDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    bottomSheetDialog.dismiss();
                    Toasty.info(getActivity(),"Event Created Successfully",Toast.LENGTH_SHORT,false).show();
                }else {
                    Toasty.warning(getActivity(),task.getException().getMessage(),Toast.LENGTH_SHORT,false).show();
                    bottomSheetDialog.dismiss();
                }
            }
        });

    }


}
