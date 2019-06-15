package com.example.interestingplaces;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class PlacesFragment extends Fragment {


    public String[] nazwy_miejsc = new String[20];


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_places, null);

    }

    public void nadaj_nazwy(){

        nazwy_miejsc[0]="WAWEL";
        nazwy_miejsc[1]="LEWIATAN";
        nazwy_miejsc[2]="BIEDRONKA";
        nazwy_miejsc[3]="HOGWART";
        nazwy_miejsc[4]="LIMANOWA";

    }

    @Override
    public void onResume()
    {
        super.onResume();
        LinearLayout MainLL= (LinearLayout) getView().findViewById(R.id.lista_miejsc);

        getView().findViewById(R.id.button).setVisibility(View.GONE);

        nadaj_nazwy();

        for(int i=0; i<5; i++){

            Button przyciski=(Button) getView().findViewById(R.id.button);
            Button przciski = new Button(getActivity());

            przciski.setText(nazwy_miejsc[i]);
            przciski.setTextSize(12);

            przciski.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));


            MainLL.addView(przciski);
        }

    }


}


