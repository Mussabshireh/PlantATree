package com.treeshopapp.treeshop;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ShoppingCart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        ListView myListView=(ListView) findViewById(R.id.CartItem);



        final ArrayList<Tree> OrderList=new ArrayList<>();


        ShoppingCartAdapter adapter=new ShoppingCartAdapter(this, R.layout.shoppingcartitem, OrderList);


        myListView.setAdapter(adapter);

    }
}
