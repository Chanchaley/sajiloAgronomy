package com.example.chanchaley.prototype;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    Animation fade_in,fade_out;
    ViewFlipper viewflipper;
    TextView connect;
    TextView ss;
    TextView si;
    ImageView gall;
    ImageView maill;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Flipper
        viewflipper = (ViewFlipper) this.findViewById(R.id.flipper1);

        fade_in = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in);
        fade_out = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out);

        viewflipper.setInAnimation(fade_in);
        viewflipper.setInAnimation(fade_out);

        //sets auto flipping
        viewflipper.setAutoStart(true);
        viewflipper.setFlipInterval(5000);
        viewflipper.startFlipping();

        // Buttons
        connect = (TextView) findViewById(R.id.check_connection);
        ss = (TextView) findViewById(R.id.soilstudy);
        si = (TextView) findViewById(R.id.s_irrigation);
        gall = (ImageView)findViewById(R.id.gallery);
        maill = (ImageView)findViewById(R.id.mail);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent;
                intent = new Intent(MainActivity.this, arduino.class);
                startActivity(intent);

                Toast.makeText(MainActivity.this, "This is Where you can see if you are connected to arduino or not", Toast.LENGTH_LONG).show();

            }
        });

        ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(MainActivity.this, soil_study.class);
                startActivity(intent);

                Toast.makeText(MainActivity.this, " Choose your topic of interest ", Toast.LENGTH_SHORT).show();
            }
        });

        si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(MainActivity.this, irrigation.class);
                startActivity(intent);

                Toast.makeText(MainActivity.this, "Irrigation portion of सजिलो Agronomy ", Toast.LENGTH_SHORT).show();
            }
        });

        gall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(MainActivity.this, gallery.class);
                startActivity(intent);
            }
        });

        maill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.gmail.com"));
                startActivity(intent);
            }
        });



        //Toolbar and floatbutton
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Contact us: sajiloagro@gmail.com", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        FloatingActionButton feed = (FloatingActionButton) findViewById(R.id.feedback);
        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "You can inbox us your suggesions", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.facebook.com/messages/t/sajiloagro"));
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
}
