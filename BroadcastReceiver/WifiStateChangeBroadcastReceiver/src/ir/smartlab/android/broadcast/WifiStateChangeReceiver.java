package ir.smartlab.android.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.widget.Toast;

public class WifiStateChangeReceiver extends BroadcastReceiver {
	public WifiStateChangeReceiver() {
	}

	@Override
	public void onReceive(Context context, Intent intent) {

		abortBroadcast();
		
		WifiManager wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		switch (wifiManager.getWifiState()) {
		case WifiManager.WIFI_STATE_DISABLED:
			Toast.makeText(context, "WIFI_STATE_DISABLED", Toast.LENGTH_LONG)
					.show();
			break;
		case WifiManager.WIFI_STATE_ENABLED:
			Toast.makeText(context, "WIFI_STATE_ENABLED", Toast.LENGTH_LONG)
					.show();
			break;
		case WifiManager.WIFI_STATE_DISABLING:
			Toast.makeText(context, "WIFI_STATE_DISABLING", Toast.LENGTH_LONG)
					.show();
			break;
		case WifiManager.WIFI_STATE_ENABLING:
			Toast.makeText(context, "WIFI_STATE_ENABLING", Toast.LENGTH_LONG)
					.show();
			break;
		case WifiManager.WIFI_STATE_UNKNOWN:
			Toast.makeText(context, "WIFI_STATE_UNKNOWN", Toast.LENGTH_LONG)
					.show();
			break;
		}
	}
}
