package com.addplugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;

/**
 * This class echoes a string called from JavaScript.
 */
public class addplugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("addMethod")) {
            // String message = args.getString(0);
            String input1 = args.getString(0);
            String input2 = args.getString(1);
            this.addMethod(input1, input2, callbackContext);
            return true;
        } else if (action.equals("subtract")) {
            // String message = args.getString(0);
            String input1 = args.getString(0);
            String input2 = args.getString(1);
            this.subtractMethod(input1, input2, callbackContext);
            return true;
        } else if (action.equals("openCamera")) {
            // String message = args.getString(0);
            this.openCamera(callbackContext);
            return true;
        }
        return false;
    }

    private void addMethod(String input1, String input2, CallbackContext callbackContext) {
        callbackContext.success(Integer.parseInt(input1) + Integer.parseInt(input2));
        // if (message != null && message.length() > 0) {
        // callbackContext.success(message);
        // } else {
        // callbackContext.error("Expected one non-empty string argument.");
        // }
    }

    private void subtractMethod(String input1, String input2, CallbackContext callbackContext) {
        Integer out = Integer.parseInt(input1) - Integer.parseInt(input2);
        Log.e("Test", "" + out);
        // Test test = new Test();
        // test.toast(cordova.getContext());
        callbackContext.success(Integer.parseInt(input1) - Integer.parseInt(input2));
        // if (message != null && message.length() > 0) {
        // callbackContext.success(message);
        // } else {
        // callbackContext.error("Expected one non-empty string argument.");
        // }
    }

    private void openCamera(CallbackContext callbackContext) {
        Log.e("Test", "test");
        callbackContext.success(Integer.parseInt("89") - Integer.parseInt("3"));
        // Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        // this.cordova.getActivity().startActivity(intent);
        // startActivity(intent);

    }
}
