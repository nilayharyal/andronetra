package com.pro.gui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class YourReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, AndroguiActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}