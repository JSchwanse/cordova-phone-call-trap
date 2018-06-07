package io.gvox.phonecalltrap;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.lang.StringBuilder;
import java.lang.String;
import android.util.Log;
import android.widget.Toast;
import android.telephony.TelephonyManager;
import android.content.pm.PackageManager;


public class PhoneCallReceiver extends BroadcastReceiver {

    private static final String TAG = "MyBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
		// build log
        StringBuilder sb = new StringBuilder();
        sb.append("Action: " + intent.getAction() + "\n");
        sb.append("URI: " + intent.toUri(Intent.URI_INTENT_SCHEME).toString() + "\n");
        String log = sb.toString();
        Log.d(TAG, log);
        Toast.makeText(context, log, Toast.LENGTH_LONG).show();
		
		//read incoming number
		String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
		
		// start app with params
		if (intent.getStringExtra(TelephonyManager.EXTRA_STATE_RINGING)){
		PackageManager pm = context.getPackageManager();
		Intent launchIntent = pm.getLaunchIntentForPackage("de.mpssolutions.twin.mobile");
		launchIntent.putExtra("incoming_number", incomingNumber);
		context.startActivity(launchIntent);}
    }

}