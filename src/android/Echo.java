package com.publicislondon.plugins.echo;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import android.util.Log;

public class Echo extends CordovaPlugin  {

	public static final String TAG = "Echo";
	public static final String ACTION_ECHO = "echo";

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		Log.i(TAG, "excecute function ran with action: " + action);
		
		if (ACTION_ECHO.equals(action)) {

			Log.v(TAG, "execute: action: " + action);

			try {

				String message = args.getString(0);
				this.echo(message, callbackContext);
				return true;

			} catch (JSONException e) {

				Log.e(TAG, "execute: Got JSON Exception " + e.getMessage());
				callbackContext.error(e.getMessage());

			}
		}

		return false;
	}

	private void echo(final String message, final CallbackContext callbackContext) {
		Log.i(TAG, "echo(): " + message);

		cordova.getThreadPool().execute(new Runnable() {
			public void run() {
				if (message != null && message.length() > 0) {
					callbackContext.success(message);
				} else {
					callbackContext.error("Expected one non-empty string argument.");
				}
			}
		});
	}

}