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

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Detector.Detections;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

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
        // Test test = new Test();
        // test.toast(cordova.getContext());
        callbackContext.success(Integer.parseInt(input1) - Integer.parseInt(input2));
    }

    private void openCamera(CallbackContext callbackContext) {
        // callbackContext.success(Integer.parseInt("89") - Integer.parseInt("3"));
        try {
            // Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            // Let's use the intent and see what happens
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            this.cordova.getActivity().startActivity(intent);
            callbackContext.success("Akshay");

            BarcodeDetector barcodeDetector = BarcodeDetector.Builder(this)
                    .setBarcodeFormats(Barcode.ALL_FORMATS)
                    .build();
            CameraSource cameraSource = CameraSource.Builder(this, barcodeDetector)
                    .setRequestedPreviewSize(1920, 1080)
                    .setAutoFocusEnabled(true) // you should add this feature
                    .build();
            // SurfaceVi surfaceView!!.holder.addCallback(object : SurfaceHolder.Callback {
            // override fun surfaceCreated(holder: SurfaceHolder) {
            // try {
            // if (ActivityCompat.checkSelfPermission(
            // this@MainActivity,
            // Manifest.permission.CAMERA
            // ) == PackageManager.PERMISSION_GRANTED
            // ) {
            // cameraSource?.start(surfaceView!!.holder)
            // } else {
            // ActivityCompat.requestPermissions(
            // this@MainActivity,
            // arrayOf<String>(Manifest.permission.CAMERA),
            // REQUEST_CAMERA_PERMISSION
            // )
            // }
            // } catch (e: IOException) {
            // e.printStackTrace()
            // }

            // } catch (Exception e) {
            // // callbackContext.success(e);
            // }

        } catch (Exception e) {
            // callbackContext.success(e);
        }
    }

    private void scanMethod() {

    }
}
