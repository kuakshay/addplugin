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
import android.provider.MediaStore;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.hero.barcode.BarCode;
import com.hero.barcode.ScannerActivity;

/**
 * This class echoes a string called from JavaScript.
 */
public class addplugin extends CordovaPlugin {
    // val intentLauncher =
    // registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
    // result ->
    //
    // if (result.resultCode == Activity.RESULT_OK) {
    // val data= result.data?.getStringExtra("key1")
    // Log.d("Datartrtrr",data!!)
    // barcodeText!!.text=data.toString()
    // }
    // }
    CallbackContext callbackContext = null;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == Activity.RESULT_OK) {
            String data = intent.getStringExtra("key1");
            System.out.println("data" + data);
            Log.d("Datartrtrr", data);
            callbackContext.success(data);
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
        // if (message != null && message.length() > 0) {
        // callbackContext.success(message);
        // } else {
        // callbackContext.error("Expected one non-empty string argument.");
        // }
    }

    private void subtractMethod(String input1, String input2, CallbackContext callbackContext) {
        Integer out = Integer.parseInt(input1) - Integer.parseInt(input2);
        // Test test = new Test();
        // test.toast(cordova.getContext());
        callbackContext.success(Integer.parseInt(input1) - Integer.parseInt(input2));
    }

    private void openCamera(CallbackContext callbackContext) {
        this.callbackContext = callbackContext;
        // callbackContext.success(Integer.parseInt("89") - Integer.parseInt("3"));

        // Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // this.cordova.getActivity().startActivity(intent);
        // callbackContext.success("Akshay");

        // BarCode.CreateBarCode(this.cordova.getActivity());
        try {
            // BarCode.Companion.CreateBarCode(this.cordova.getContext());
            Intent intent = new Intent(this.cordova.getContext(), ScannerActivity.class);
            this.cordova.getActivity().startActivityForResult(intent, 100);

            // callbackContext.success("Akshay");
        } catch (Exception e) {
            Log.e("err", "" + e);
        }

    }

    private void scanMethod() {

    }

}
