package com.addplugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PermissionHelper;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.provider.MediaStore;
import android.widget.Toast;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.hero.barcode.BarCode;
import com.hero.barcode.ScannerActivity;

/**
 * This class echoes a string called from JavaScript.
 */
public class addplugin extends CordovaPlugin {
    CallbackContext callbackContext = null;
    private String[] permissions = { Manifest.permission.CAMERA };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 100) {
            String data = intent.getStringExtra("key1");
            callbackContext.success(data);
        } else {
            callbackContext.success("Error Occurred");
        }
    }

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
    }

    private void subtractMethod(String input1, String input2, CallbackContext callbackContext) {
        Integer out = Integer.parseInt(input1) - Integer.parseInt(input2);
        callbackContext.success(Integer.parseInt(input1) - Integer.parseInt(input2));
    }

    private void openCamera(CallbackContext callbackContext) {
        this.callbackContext = callbackContext;
        if (!hasPermisssion()) {
            requestPermissions(0);
        } else {
            scanData();
        }
    }

    public void scanData() {
        try {
            Intent intent = new Intent(this.cordova.getContext(), ScannerActivity.class);
            this.cordova.startActivityForResult(this, intent, 100);
        } catch (Exception e) {
            Log.e("err", "" + e);
        }
    }

    /**
     * check application's permissions
     */
    public boolean hasPermisssion() {
        for (String p : permissions) {
            if (!PermissionHelper.hasPermission(this, p)) {
                return false;
            }
        }
        return true;
    }

    /**
     * We override this so that we can access the permissions variable, which no
     * longer exists in
     * the parent class, since we can't initialize it reliably in the constructor!
     *
     * @param requestCode The code to get request action
     */

    public void requestPermissions(int requestCode) {
        PermissionHelper.requestPermissions(this, requestCode, permissions);
    }

    /**
     * processes the result of permission request
     * 
     * @param requestCode  The code to get request action
     * @param permissions  The collection of permissions
     * @param grantResults The result of grant
     */

    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults)
            throws JSONException {
        PluginResult result;
        for (int r : grantResults) {
            if (r == PackageManager.PERMISSION_DENIED) {
                Log.d(LOG_TAG, "Permission Denied!");
                result = new PluginResult(PluginResult.Status.ILLEGAL_ACCESS_EXCEPTION);
                this.callbackContext.sendPluginResult(result);
                return;
            }
        }
        switch (requestCode) {
            case 0:
                scanData();
                break;
        }
    }

    /**
     * This plugin launches an external Activity when the camera is opened, so we
     * need to implement the save/restore API in case the Activity gets killed
     * by the OS while it's in the background.
     */
    public void onRestoreStateForActivityResult(Bundle state, CallbackContext callbackContext) {
        this.callbackContext = callbackContext;
    }

}
