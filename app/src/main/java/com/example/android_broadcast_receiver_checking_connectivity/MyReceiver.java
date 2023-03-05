package com.example.android_broadcast_receiver_checking_connectivity;


import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_broadcast_receiver_checking_connectivity.utils.NetworkUtil;

public class MyReceiver extends BroadcastReceiver {
    Dialog dialog;
    TextView nettext;

    @Override
    public void onReceive(Context context, Intent intent) {
        String status = NetworkUtil.getConnectivityStatusString(context);
        dialog = new Dialog(context, android.R.style.Theme_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.customdialog);
        Button restartapp = (Button) dialog.findViewById(R.id.restartapp);
        nettext = (TextView) dialog.findViewById(R.id.nettext);

        restartapp.setOnClickListener(v -> {
            ((Activity) context).finish();
            //Log.d("clickedbutton","yes");
            Intent i = new Intent(context, MainActivity.class);
            context.startActivity(i);
        });
        //Log.d("network",status);
        if (status.isEmpty() || status.equals("No internet is available")) {//||status.equals("No Internet Connection")) {
            status = "No Internet Connection";
            dialog.show();
        }
        Toast.makeText(context, status, Toast.LENGTH_LONG).show();
    }
}
