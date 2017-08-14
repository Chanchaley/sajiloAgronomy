package com.example.chanchaley.prototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class soil_study extends AppCompatActivity {

    TextView first;
    TextView second;
    TextView third;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soil_study);

        first = (TextView)findViewById(R.id.soil_texture);
        second = (TextView)findViewById(R.id.soil_nepal);
        third = (TextView)findViewById(R.id.plants);

        third.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent;
                intent = new Intent(soil_study.this, trees.class);
                startActivity(intent);
            }
        });

        first.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(soil_study.this, Soil1.class);
                startActivity(intent);
            }
        });
        
    }
}
