package com.example.interestingplaces;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity implements  BottomNavigationView.OnNavigationItemSelectedListener {

    Fragment mapFragment;
    Fragment profileFragment;
    Fragment placesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//ustawiam główny layout

        //Ustawiam funkcje do nawigacji
        BottomNavigationView navigation = findViewById(R.id.mainNavigation);
        navigation.setOnNavigationItemSelectedListener(this);

        //definiuje fragmenty
        mapFragment = new MapFragment();
        profileFragment = new ProfileFragment();
        placesFragment = new PlacesFragment();

        //laduje fragment z mapa
        loadFragment(mapFragment);


    }

    /**
     * Sing out user
     */
    public void signOut() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
                        MainActivity.this.startActivity(myIntent);
                    }
                });
    }

    /**
     * Load fragment of layout to frame_container
     */

    private boolean loadFragment(Fragment fragment) {
        if(fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, fragment)
                    .commit();
            return true;
        }
        return false;

    }

    /**
     *
     * @param menuItem
     * @describe W zaleznosci od kliknietego przycisku w nawigacji laduje odpowiedni fragment
     * @return
     */

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.navigation_profile:
                fragment = profileFragment;
                break;
            case R.id.navigation_places:
                fragment = placesFragment;
                break;
            case R.id.navigation_map:
                fragment = mapFragment;
                break;
        }

        return loadFragment(fragment);
    }
}
