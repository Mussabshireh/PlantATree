package com.treeshopapp.treeshopapp;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.treeshopapp.treeshop.Checkout;
import com.treeshopapp.treeshop.LoginPage;
import com.treeshopapp.treeshop.Profile;
import com.treeshopapp.treeshop.R;
import com.treeshopapp.treeshop.ShoppingCart;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_cart:
                Intent h = new Intent(MainActivity.this, ShoppingCart.class);
                startActivity(h);
                break;
            case R.id.nav_pay:
                Intent i = new Intent(MainActivity.this, Checkout.class);
                startActivity(i);
                break;
            case R.id.nav_profile:
                Intent j = new Intent(MainActivity.this, Profile.class);
                startActivity(j);
                break;
            case R.id.nav_logoff:
                Intent k = new Intent(MainActivity.this, LoginPage.class);
                startActivity(k);
                break;
        }
        return true;
    }
}