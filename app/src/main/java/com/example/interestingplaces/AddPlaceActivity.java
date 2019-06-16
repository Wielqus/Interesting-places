package com.example.interestingplaces;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;


public class AddPlaceActivity extends AppCompatActivity {

    MaterialButton addButton;
    MaterialButton selectPlaceButton;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private EditText name;
    private EditText description;
    private LatLng position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);
        name = findViewById(R.id.placeName);
        description = findViewById(R.id.placeDescription);
        selectPlaceButton = findViewById(R.id.selectPlaceButton);
        position = new LatLng(20.1123,20.323);

        selectPlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddPlaceActivity.this, SelectPlaceActivity.class);
                startActivityForResult(intent, 200);

            }
        });


        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> place = new HashMap<>();
                place.put("name", name.getText().toString());
                place.put("description", description.getText().toString());
                place.put("position", new GeoPoint(position.latitude,position.longitude));
                db.collection("Places")
                        .add(place)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Intent myIntent = new Intent(AddPlaceActivity.this, MainActivity.class);
                                AddPlaceActivity.this.startActivity(myIntent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("FIRESTORE", "Error adding document", e);
                            }
                        });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {

            Bundle bundle = data.getExtras();
            String positionString = bundle.getString("position");

            position = new Gson().fromJson(positionString, LatLng.class);


        } else if(resultCode == RESULT_CANCELED) {
            Log.d("FingerPainter", "MainActivity canceled");

        }
    }
}
