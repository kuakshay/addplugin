package com.addplugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import com.example.mylibrary.ScanActivity;

/**
 * This class echoes a string called from JavaScript.
 */
public class addplugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("addMethod")) {
            this.addMethod(args, callbackContext);
            return true;
        } else if (action.equals("scanCode")) {
            this.scanCode(args, callbackContext);
            return true;
        }
        return false;
    }

    private void addMethod(JSONArray args, CallbackContext callbackContext) {

        Intent browserIntent = new Intent(addplugin.this, ScanActivity.class);
        cordova.getContext().startActivity(browserIntent);
        // callbackContext.success(Integer.parseInt(input1) + Integer.parseInt(input2));
    }

    private void scanCode(JSONArray args, CallbackContext callbackContext) {

        callbackContext.success(result);
    }

}
