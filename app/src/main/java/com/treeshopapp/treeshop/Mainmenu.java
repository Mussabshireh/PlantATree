package com.treeshopapp.treeshop;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
<<<<<<< HEAD
import android.widget.Spinner;
import android.widget.Toast;
=======
>>>>>>> origin/master

import com.treeshopapp.treeshopapp.MainActivity;

import java.util.ArrayList;

public class Mainmenu extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu_activity);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        ListView myListView=(ListView) findViewById(R.id.TreeList);

        Tree apple=new Tree("apple tree", 200, R.drawable.appletree);
        Tree pear=new Tree("pear tree", 210, R.drawable.peartree);
        Tree kiwi=new Tree("kiwi tree", 215,R.drawable.kiwitree);
        Tree grape=new Tree("grape tree", 300, R.drawable.grapetree);
        Tree sakura=new Tree("sakura tree", 270, R.drawable.sakuratree);
        Tree mango=new Tree("mango stree", 290, R.drawable.mangotree);

        final ArrayList<Tree> TreeList=new ArrayList<>();
        TreeList.add(apple);
        TreeList.add(pear);
        TreeList.add(kiwi);
        TreeList.add(grape);
        TreeList.add(sakura);
        TreeList.add(mango);

        TreeListAdapter adapter=new TreeListAdapter(this, R.layout.itemrow, TreeList);


        myListView.setAdapter(adapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent();
                intent.putExtra("Name", TreeList.get(i).getTheNameOfTree());
                intent.putExtra("Image", TreeList.get(i).getImgURL());
                intent.putExtra("Price", TreeList.get(i).getStringPirce());

                intent.setClass(Mainmenu.this, OrderTreePage.class);
                startActivity(intent);
            }
        });

        if(ContextCompat.checkSelfPermission(Mainmenu.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){

        }



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
            case R.id.nav_accessories:
                Intent g = new Intent(Mainmenu.this, PlantAccessories.class);
                startActivity(g);
                break;
            case R.id.nav_cart:
                Intent h = new Intent(Mainmenu.this, ShoppingCart.class);
                startActivity(h);
                break;
            case R.id.nav_pay:
                Intent i = new Intent(Mainmenu.this, Checkout.class);
                startActivity(i);
                break;
            case R.id.nav_profile:
                Intent j = new Intent(Mainmenu.this, Profile.class);
                startActivity(j);
                break;
            case R.id.nav_logoff:
                Intent k = new Intent(Mainmenu.this, LoginPage.class);
                startActivity(k);
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {

    }
}