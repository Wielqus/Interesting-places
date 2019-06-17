package com.example.interestingplaces;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.card.MaterialCardView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class PlacesFragment extends Fragment {


    public String[] nazwy_miejsc = new String[20];
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private LinearLayout Main;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_places, null);

    }


    @Override
    public void onResume()
    {
        super.onResume();
        Main = (LinearLayout) getView().findViewById(R.id.lista_miejsc);

        final Activity thisActivity = this.getActivity();
        db.collection("Places")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (final QueryDocumentSnapshot document : task.getResult()) {

                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT
                                );

                                MaterialCardView card = new MaterialCardView(getActivity());

                                card.setContentPadding(15, 15, 15, 15);
                                params.setMargins(0, 10, 0, 10);
                                card.setLayoutParams(params);

                                LinearLayout column = new LinearLayout(getActivity());
                                column.setLayoutParams(new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.MATCH_PARENT
                                ));
                                column.setOrientation(LinearLayout.VERTICAL);

                                TextView name = new TextView(getActivity());
                                name.setLayoutParams(params);
                                name.setTextSize(18);
                                name.setTypeface(null, Typeface.BOLD);
                                name.setText(document.getData().get("name").toString());
                                column.addView(name);

                                TextView description = new TextView(getActivity());
                                description.setLayoutParams(params);
                                description.setTextSize(14);
                                description.setText(document.getData().get("description").toString());
                                column.addView(description);

                                card.addView(column);

                                Main.addView(card);

                            }
                        } else {
                            Log.w("Places", "Error getting documents.", task.getException());
                        }
                    }
                });



    }


}


