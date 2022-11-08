package com.addplugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

            this.subtractMethod(args, callbackContext);
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

    private void subtractMethod(JSONArray args, CallbackContext callbackContext) {
        String input1 = args.getString(0);
        String input2 = args.getString(1);
        callbackContext.success(Integer.parseInt(input1) - Integer.parseInt(input2));
        // if (message != null && message.length() > 0) {
        // callbackContext.success(message);
        // } else {
        // callbackContext.error("Expected one non-empty string argument.");
        // }
    }
}
