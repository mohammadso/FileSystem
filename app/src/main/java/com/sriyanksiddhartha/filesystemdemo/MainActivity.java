package com.sriyanksiddhartha.filesystemdemo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.sriyanksiddhartha.filesystemdemo.storageoptions.CacheStorageDemo;
import com.sriyanksiddhartha.filesystemdemo.storageoptions.ExternalStorageDemo;
import com.sriyanksiddhartha.filesystemdemo.storageoptions.InternalStorageDemo;

/**
 * 	Author: Sriyank Siddhartha
 *
 * 	Module 2: Working with Internal Storage
 *
 * 			"BEFORE" project
 * */
public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		int grantedNum = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
		if (grantedNum != PackageManager.PERMISSION_GRANTED){
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 30 );
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		switch (requestCode) {

			case 30: {

				if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					Toast.makeText(this, "Tanks For permission", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(this, "go fuck your ass", Toast.LENGTH_SHORT).show();
				}
			}
		}
	}

	public void openInternalStorageDemoActivity(View view) {

		Intent intent = new Intent(this, InternalStorageDemo.class);
		startActivity(intent);
	}

	public void openCacheStorageDemoActivity(View view) {

		Intent intent = new Intent(this, CacheStorageDemo.class);
		startActivity(intent);
	}

	public void openExternalStorageDemoActivity(View view) {

		Intent intent = new Intent(this, ExternalStorageDemo.class);
		startActivity(intent);
	}
}
