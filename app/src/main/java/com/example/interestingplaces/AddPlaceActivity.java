package com.example.interestingplaces;

import android.content.Intent;
import android.net.Uri;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddPlaceActivity extends AppCompatActivity {

    MaterialButton addImagesButton;
    MaterialButton selectPlaceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);
        addImagesButton = findViewById(R.id.placeImages);
        addImagesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent intent = new Intent();
                intent.setAction(android.content.Intent.ACTION_VIEW);
                Uri uri = Uri.parse("file://"etAbsolutePath());
                intent.setDataAndType(uri,"image/*");
                startActivity(intent);
                */
            }
        });

        selectPlaceButton = findViewById(R.id.selectPlaceButton);
        selectPlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(AddPlaceActivity.this, SelectPlaceActivity.class);
                AddPlaceActivity.this.startActivity(myIntent);
            }
        });

    }
}
