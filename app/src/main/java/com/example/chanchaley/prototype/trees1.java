package com.example.chanchaley.prototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class trees1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trees1);

        String treess[] = {"1. Common Fodder Tree (उतिस)", "2. Indian gooseberry (अमला)", "3. Mountain Ebony (कोइरालो)",
                "4. Nepalease Butter Tree (चिउरी )", "5. sacred fig tree (पीपल)", "6. Mango Tree (आपको रुख)", "7. Garuga (दबदबे)",
        "8. Barro (बर्रो )", "9. Coral Tree (फलेदो)", "10. Orange Tree (सुन्तलाको रुख)", "11. Jack Fruit(रुख कटहर )",
                "12. Banayan Tree (बर) "};
        final ListAdapter myadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, treess);
        ListView myview = (ListView)findViewById(R.id.listview2);
        myview.setAdapter(myadapter);


        myview.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent intent = new Intent(view.getContext(),explain_trees.class);
                    startActivityForResult(intent,0);
                }

            }
        });
    }
}
