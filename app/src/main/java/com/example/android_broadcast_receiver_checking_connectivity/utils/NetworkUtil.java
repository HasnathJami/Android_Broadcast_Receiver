package com.example.android_broadcast_receiver_checking_connectivity.utils;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.provider.Settings;

public class NetworkUtil {

    public static String getConnectivityStatusString(Context context) {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        String status = null;
//        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//        if (activeNetwork != null) {
//            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
//                status = "Wifi enabled";
//                return status;
//            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
//                status = "Mobile data enabled";
//                return status;
//            }
//        } else {
//            status = "No internet is available";
//            return status;
//        }
        if (Settings.System.getInt(context.getContentResolver(), Settings.Global.WIFI_ON, 0) == 1) {
            status = "Wifi enabled";
            return status;
        } else if (Settings.System.getInt(context.getContentResolver(), Settings.Global.WIFI_ON, 0) == 0) {
            status = "Wifi disabled";
            return status;
        } else if (Settings.System.getInt(context.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0) == 1) {
            status = "AirPlane enabled";
            return status;
        } else if (Settings.System.getInt(context.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0) == 0) {
            status = "AirPlane disabled";
            return status;
        }
        // else if (mBluetoothAdapter == null) {
        // Device does not support Bluetooth
        // }
//        else if (mBluetoothAdapter != null) {
//            if (!mBluetoothAdapter.isEnabled()) {
//                // Bluetooth is not enabled :)
//                status = "BlueTooth Disabled";
//                return status;
//            } else {
//                // Bluetooth is enabled
//                status = "BlueTooth Enabled";
//                return status;
//            }
//        }
        else {
            status = "No internet is available";
            return status;
        }
        // return status;

    }
}