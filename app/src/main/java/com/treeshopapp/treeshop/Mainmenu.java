package com.treeshopapp.treeshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Mainmenu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu_activity);

        ListView myListView=(ListView) findViewById(R.id.TreeList);

        Tree apple=new Tree("apple tree", 200, R.drawable.appletree);
        Tree pear=new Tree("pear tree", 210, R.drawable.peartree);
        Tree kiwi=new Tree("kiwi tree", 215,R.drawable.kiwitree);

        final ArrayList<Tree> TreeList=new ArrayList<>();
        TreeList.add(apple);
        TreeList.add(pear);
        TreeList.add(kiwi);

        TreeListAdapter adapter=new TreeListAdapter(this, R.layout.itemrow, TreeList);

//        ArrayAdapter<Tree> arrayAdapter=new ArrayAdapter<Tree>(this, R.layout.adapter_view_layout, Tree);

        myListView.setAdapter(adapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent();
                intent.putExtra("Name", TreeList.get(i).getTheNameOfTree());
                intent.putExtra("Image", TreeList.get(i).getImgURL());

                intent.setClass(Mainmenu.this, OrderTreePage.class);
                startActivity(intent);
            }
        });

    }
}
