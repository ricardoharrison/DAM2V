package com.rittz.callpermission;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.Manifest;

public class MainActivity extends AppCompatActivity {

    Button buttonCall;
    ActivityResultLauncher<String> requestPermissionLauncher;
    private static final String CALL_PHONE_PERMISSION = Manifest.permission.CALL_PHONE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCall = findViewById(R.id.buttonCall);

        buttonCall.setOnClickListener(view -> checkPermissionAndCall());

        requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted) {
                // Permission is granted. Proceed with making the call.
                makePhoneCall();
            } else {
                // Permission denied. Show a message.
                Toast.makeText(MainActivity.this, "Necesitamos permiso para llamar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkPermissionAndCall() {
        if (ContextCompat.checkSelfPermission(this, CALL_PHONE_PERMISSION) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted. Request it.

            //HACE FALTA <uses-permission...> EN EL MANIFEST
            requestPermissionLauncher.launch(CALL_PHONE_PERMISSION);
        } else {
            // Permission already granted. Make the call.
            makePhoneCall();
        }
    }

    private void makePhoneCall() {
        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:0034 634 278 409"));


        // Check if the device can handle the intent before calling startActivity
        if (phoneIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(phoneIntent);
        } else {
            Toast.makeText(MainActivity.this, "No se puede realizar la llamada", Toast.LENGTH_SHORT).show();
        }
    }
}