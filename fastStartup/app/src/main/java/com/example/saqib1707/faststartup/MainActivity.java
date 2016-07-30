package com.example.saqib1707.faststartup;

import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    TextView mainText;
    WifiManager mainWifi;
    WifiReceiver receiverWifi;
    List<ScanResult> wifiList;
    //StringBuilder sb = new StringBuilder();
    public static String TP_MAC = "9c:d6:43:d7:69:bc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainText = (TextView) findViewById(R.id.textView);
        mainWifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        if (mainWifi.isWifiEnabled() == false) {
            // If wifi disabled then enable it
            Toast.makeText(getApplicationContext(), "wifi is disabled..making it enabled",
                    Toast.LENGTH_LONG).show();

            mainWifi.setWifiEnabled(true);
        }
        receiverWifi = new WifiReceiver();
        registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        mainWifi.startScan();
        mainText.setText("Starting Scan...");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "Refresh");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        mainWifi.startScan();
        mainText.setText("Starting Scan");
        return super.onMenuItemSelected(featureId, item);
    }

    protected void onPause() {
        unregisterReceiver(receiverWifi);
        super.onPause();
    }

    protected void onResume() {
        registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }

    // Broadcast receiver class called its receive method
    // when number of wifi connections changed

    class WifiReceiver extends BroadcastReceiver {

        // This method call when number of wifi connections changed
        public void onReceive(Context c, Intent intent) {
            wifiList = mainWifi.getScanResults();
            boolean inTl = false;

            if (mainWifi.getConnectionInfo().getBSSID().equalsIgnoreCase("TP_MAC")) {
                inTl = true;
            } else {
                for (int i = 0; i < wifiList.size(); i++) {
                    ScanResult wifi = wifiList.get(i);
                    if (wifi.BSSID.equalsIgnoreCase("TP_MAC")) {
                        inTl = true;

                        WifiConfiguration conf = new WifiConfiguration();
                        conf.SSID = "\"" + wifi.SSID + "\"";
                        conf.preSharedKey = "\"67DAFF34\"";
                        mainWifi.addNetwork(conf);

                        List<WifiConfiguration> list = mainWifi.getConfiguredNetworks();
                        for (WifiConfiguration w : list) {
                            if (w.SSID != null && w.SSID.equals("\"" + wifi.SSID + "\"")) {
                                mainWifi.disconnect();
                                mainWifi.enableNetwork(w.networkId, true);
                                mainWifi.reconnect();
                                break;
                            }
                        }
                    }
                }
            }
            if(inTl){
                //mainText.setText("We're in TL!");
                Toast.makeText(getApplicationContext(),"We are in Tl",Toast.LENGTH_LONG).show();
            } else {
                //mainText.setText("We're not in TL :(");
                Toast.makeText(getApplicationContext(),"We are not in Tl",Toast.LENGTH_LONG).show();
            }

        }
    }
}
