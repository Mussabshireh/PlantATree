package com.treeshopapp.treeshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderTreePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_tree_page);

        ImageView treeImage=(ImageView) findViewById(R.id.treeImg);
        TextView treeName=(TextView) findViewById(R.id.treeName);

        treeName.setText(getIntent().getStringExtra("Name"));
        treeImage.setImageResource(getIntent().getIntExtra("Image", R.drawable.appletree));

    }
}
