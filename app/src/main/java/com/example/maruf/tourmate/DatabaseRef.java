package com.example.maruf.tourmate;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseRef {
private static DatabaseReference mainRef = FirebaseDatabase.getInstance().getReference();
public static  final DatabaseReference userRef = mainRef.child("Users");

}
