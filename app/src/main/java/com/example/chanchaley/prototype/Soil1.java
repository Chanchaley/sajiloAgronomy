package com.example.chanchaley.prototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Soil1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soil1);

        String treess[] = {"1. Alfisols", "2. Andisols", "3. Aridisols", "4. Entisols ", "5. Gelisols", "6. Histosols", "7. Inceptisols ",
                "8. Mollisols ", "9. Oxisols ", "10. Spodosols", "11. Ultisols","12. Vertisols"};
        final ListAdapter myadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, treess);
        ListView myview = (ListView)findViewById(R.id.listview3);
        myview.setAdapter(myadapter);

        myview.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent intent = new Intent(view.getContext(),explain_soil.class);
                    startActivityForResult(intent,0);
                }

            }
        });
    }
}
