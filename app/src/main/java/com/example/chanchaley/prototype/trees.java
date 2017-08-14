package com.example.chanchaley.prototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;


public class trees extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trees);

        String treess[] = {"Trees (रुखहरु)", "Shurbs", "Herbs", "Climbers", "Grass"};
        ListAdapter myadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, treess);
        ListView myview = (ListView)findViewById(R.id.list_trees);
        myview.setAdapter(myadapter);

        myview.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent intent = new Intent(view.getContext(),trees1.class);
                    startActivityForResult(intent,0);
                }
                if(position == 1){
                    Intent intent = new Intent(view.getContext(),Shrubs.class);
                    startActivityForResult(intent,1);
                }
                if(position == 2){
                    Intent intent = new Intent(view.getContext(),herbs.class);
                    startActivityForResult(intent,2);
                }
                if(position == 3){
                    Intent intent = new Intent(view.getContext(),climbers.class);
                    startActivityForResult(intent,3);
                }
                if(position == 4){
                    Intent intent = new Intent(view.getContext(),grass.class);
                    startActivityForResult(intent,4);
                }
            }
        });
    }

}
