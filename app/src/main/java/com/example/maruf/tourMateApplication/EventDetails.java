package com.example.maruf.tourMateApplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class EventDetails extends AppCompatActivity {
        private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        textView = findViewById(R.id.textViewId);
        Intent intent = getIntent();
        String eventName = intent.getStringExtra("event");
        textView.setText(eventName);
    }
}
