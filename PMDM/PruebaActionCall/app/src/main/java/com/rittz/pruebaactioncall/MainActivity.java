package com.rittz.pruebaactioncall;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button buttonCall;
    ActivityResultLauncher<String> requestPermissionLauncher;
    private static final int REQUEST_CALL_PHONE_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCall = findViewById(R.id.buttonCall);

        buttonCall.setOnClickListener(view -> launchCall());

        // Register the permissions callback, which handles the user's response to the
        // system permissions dialog. Save the return value, an instance of
        // ActivityResultLauncher, as an instance variable.
        requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted) {
                // Permission is granted. Continue the action or workflow in your
                // app.
                launchCall();
            } else {
                // Explain to the user that the feature is unavailable because the
                // feature requires a permission that the user has denied. At the
                // same time, respect the user's decision. Don't link to system
                // settings in an effort to convince the user to change their
                // decision.
                Toast.makeText(MainActivity.this, "Necesitamos permiso para llamar", Toast.LENGTH_SHORT).show();
            }
        });

    }



    private void launchCall() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE_PERMISSION);
        } else {
            // Permission has already been granted, proceed with making the call
            // Place your call logic here
            Intent phoneIntent = new Intent(Intent.ACTION_CALL);
            phoneIntent.setData(Uri.parse("tel:0034 634 278 409"));
            startActivity(phoneIntent);
        }

    }

 /*
    public void llamadaClick(View v){
        if (ContextCompat.checkSelfPermission(
                MainActivity.this, Manifest.permission.CALL_PHONE) ==
                PackageManager.PERMISSION_GRANTED) {
            // You can use the API that requires the permission.
            launchCall();
        } else {
            // You can directly ask for the permission.
            // The registered ActivityResultCallback gets the result of this request.
            requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE);
        }
    }

  */

}