package com.pooria.pooriasyelp;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;

    NavigationView navigationView;

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);


        getSupportFragmentManager().beginTransaction().replace(R.id.container, new SearchFragment()).commit();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;

        if (item.getItemId() == R.id.search) {
            fragment = new SearchFragment();

            Toast.makeText(this, "Search for restaurants", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.favorites) {

            fragment = FavoritesFragment.newInstance();

            Toast.makeText(this, "Favorites", Toast.LENGTH_SHORT).show();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}