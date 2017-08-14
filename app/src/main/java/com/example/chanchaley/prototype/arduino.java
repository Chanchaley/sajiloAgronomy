package com.example.chanchaley.prototype;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Set;


public class arduino extends AppCompatActivity{
    protected  static final int DISCOVERY_REQUEST = 1;
    /**Called when the activity is first created*/
    private BluetoothAdapter btAdapter;
    public TextView statusUpdate;
    public Button connect;
    public ImageView logo;
    public Button disconnect;
    public ImageView logo1;
    public String toastText="";
    private BluetoothDevice remoteDevice;

    BroadcastReceiver bluetoothState = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String prevStateExtra = BluetoothAdapter.EXTRA_PREVIOUS_STATE;
            String stateExtra = BluetoothAdapter.EXTRA_STATE;
            int state = intent.getIntExtra(stateExtra, -1);
            int previousState = intent.getIntExtra(prevStateExtra, -1);

            switch (state) {
                case (BluetoothAdapter.STATE_TURNING_ON): {
                    toastText = "Bluetooth is turning ON";
                    Toast.makeText(arduino.this,toastText, Toast.LENGTH_SHORT).show();
                    break;
                }

                case (BluetoothAdapter.STATE_ON): {
                    toastText = "Bluetooth is already ON";
                    Toast.makeText(arduino.this,toastText, Toast.LENGTH_SHORT).show();
                    setupUI();
                    break;

                }

                case (BluetoothAdapter.STATE_TURNING_OFF): {
                    toastText = "BlueTooth Turning Off";
                    Toast.makeText(arduino.this, toastText, Toast.LENGTH_SHORT).show();
                    break;
                }

                case (BluetoothAdapter.STATE_OFF): {
                    toastText = "Bluetooth is already off";
                    Toast.makeText(arduino.this, toastText, Toast.LENGTH_SHORT).show();
                    setupUI();
                    break;
                }
            }
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arduino);
        setupUI();
    }

    private void setupUI() {
        //get references
        final TextView statusUpdate = (TextView) findViewById(R.id.device_name);
        final Button connect = (Button) findViewById(R.id.bluetoothh);
        final Button disconnect = (Button) findViewById(R.id.bluetooth_status);
        final ImageView logo = (ImageView) findViewById(R.id.blu);
        final ImageView logo1 = (ImageView) findViewById(R.id.androidd);

        //set display view

        disconnect.setVisibility(View.GONE);
        logo.setVisibility(View.GONE);
        logo1.setVisibility(View.GONE);
        btAdapter = BluetoothAdapter.getDefaultAdapter();

        if(btAdapter.isEnabled()){
            String address = btAdapter.getAddress();
            String name = btAdapter.getName();
            String statusText = name + ":" + address;
            statusUpdate.setText(statusText);
            disconnect.setVisibility(View.VISIBLE);
            logo.setVisibility(View.VISIBLE);
            logo1.setVisibility(View.VISIBLE);
            connect.setVisibility(View.GONE);
        }

        else{

            connect.setVisibility(View.VISIBLE);
            statusUpdate.setText("BLUETOOTH ------> OFF");
            Toast.makeText(arduino.this, "The Bluetooth service is off", Toast.LENGTH_SHORT).show();
        }

        connect.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //String actionStateChanged = BluetoothAdapter.ACTION_STATE_CHANGED;
                // String actionRequestEnable = BluetoothAdapter.ACTION_REQUEST_ENABLE;
                //IntentFilter filter = new IntentFilter(actionStateChanged);
                //registerReceiver(bluetoothState, filter);
                //startActivityForResult(new Intent(actionRequestEnable), 0);
                String scanModeChanged = BluetoothAdapter.ACTION_SCAN_MODE_CHANGED;
                String beDiscoverable = BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE;
                IntentFilter filter = new IntentFilter(scanModeChanged);
                registerReceiver(bluetoothState, filter);
                startActivityForResult(new Intent(beDiscoverable), DISCOVERY_REQUEST);
            }
        }); //end connect OnClickListener

        disconnect.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                btAdapter.disable();
                disconnect.setVisibility(View.GONE);
                logo.setVisibility(View.GONE);
                logo1.setVisibility(View.GONE);
                connect.setVisibility(View.VISIBLE);
                statusUpdate.setText("BLUETOOTH ------> OFF");
            }
        }); //end disconnect onClickListener

    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data){
        if(requestCode == DISCOVERY_REQUEST){
            Toast.makeText(arduino.this, "Discovery in Process", Toast.LENGTH_SHORT).show();
            setupUI();
            findDevices();
        }
    }

    private void findDevices() {
        String lastUsedRemoteDevice = getLastUsedRemoteBTDevice();
        if (lastUsedRemoteDevice != null) {
            toastText = "Checking for known devices, namely:" + lastUsedRemoteDevice;
            Toast.makeText(arduino.this, toastText, Toast.LENGTH_SHORT).show();

            Set<BluetoothDevice> pairedDevices = btAdapter.getBondedDevices();
            for (BluetoothDevice pairedDevice : pairedDevices) {
                if (pairedDevice.getAddress().equals(lastUsedRemoteDevice)) {
                    toastText = "Found Device:" + pairedDevice.getName() + "@" + lastUsedRemoteDevice;
                    Toast.makeText(arduino.this, toastText, Toast.LENGTH_SHORT).show();
                    remoteDevice = pairedDevice;
                }
            }
        } //end if

        if (remoteDevice == null) {
            toastText = "Starting discovery for remote devices.....*";
            Toast.makeText(arduino.this, toastText, Toast.LENGTH_SHORT).show();
            //start discovery
            if (btAdapter.startDiscovery()) {
                toastText = " Discovery Started.. Scanning for devices";
                Toast.makeText(arduino.this, toastText, Toast.LENGTH_SHORT).show();
                registerReceiver(discoveryResult, new IntentFilter(BluetoothDevice.ACTION_FOUND));
            }

        }
    }// end find devices

    // create a BroadcastReceiver to receive device discovery
    BroadcastReceiver discoveryResult = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String remoteDeviceName = intent.getStringExtra(BluetoothDevice.EXTRA_NAME);
            BluetoothDevice remoteDevice;
            remoteDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            toastText = "Discovered:" + remoteDeviceName;
            Toast.makeText(arduino.this, toastText, Toast.LENGTH_SHORT).show();
            //statusUpdate.setText(statusText);
        }
    };



    private String getLastUsedRemoteBTDevice(){

        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        String result = prefs.getString("LAST_REMOTE_DEVICE_ADDRESS", null);
        return result;
    }
}


