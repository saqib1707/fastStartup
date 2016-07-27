package com.example.saqib1707.faststartup;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    String networkSSID = "test";
    String networkPass = "pass";
    Button buttonscan,buttonConnect;
    //ArrayList<String> list;
    //ArrayAdapter<String> arrayAdapter;
    ListView lv;
    TextView txt;
    WifiManager wifi;
    WifiScanReceiver wifiReceiver;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    public void msg(String str){
        Toast.makeText(getApplicationContext(),str,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonscan=(Button)findViewById(R.id.button_scan);
       // lv = (ListView) findViewById(R.id.listView);
        txt=(TextView)findViewById(R.id.textView);
        wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        if (!wifi.isWifiEnabled()){
            wifi.setWifiEnabled(true);
            msg("Wifi turned on");
        }else{
            msg("Wifi is already turned on");
        }
        wifiReceiver = new WifiScanReceiver();
        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        /*buttonscan.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        wifi.startScan();
                        Toast.makeText(getApplicationContext(),"Scanning....",Toast.LENGTH_SHORT).show();
                    }
                }
        );*/
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(0,0,0,"Refresh");
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onMenuItemSelected(int featureId,MenuItem item){
        wifi.startScan();
        msg("Scanning ...");
        return super.onMenuItemSelected(featureId,item);
    }
    @Override
    public void onPause() {
        unregisterReceiver(wifiReceiver);
        super.onPause();
    }
    @Override
    public void onResume() {
        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }

    private class WifiScanReceiver extends BroadcastReceiver {
        public void onReceive(Context c, Intent intent) {
            ArrayList list=new ArrayList();
            List<ScanResult> wifiScanList = wifi.getScanResults();
            for (int i = 0; i < wifiScanList.size(); i++) {
                list.add((wifiScanList.get(i)).toString());
            }
            txt.setText(list.toString());
            //ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_dropdown_item_1line,list);
            //lv.setAdapter(adapter);
            //lv.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, wifis));
        }
    }
}
