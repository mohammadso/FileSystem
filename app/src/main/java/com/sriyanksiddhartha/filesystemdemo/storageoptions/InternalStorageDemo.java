package com.sriyanksiddhartha.filesystemdemo.storageoptions;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sriyanksiddhartha.filesystemdemo.MainActivity;
import com.sriyanksiddhartha.filesystemdemo.R;
import com.sriyanksiddhartha.filesystemdemo.displayscreens.InternalStorageDisplay;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class InternalStorageDemo extends AppCompatActivity {

	private EditText etFileName, etMessage, etFileToDelete;
	private TextView txvInternalStoragePath, txvFilesList;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.internal_storage);

		etFileName 		= (EditText) findViewById(R.id.etFileName);
		etMessage 		= (EditText) findViewById(R.id.etMessage);
		etFileToDelete 	= (EditText) findViewById(R.id.etFileToDelete);

		txvInternalStoragePath 	= (TextView) findViewById(R.id.txvInternalStoragePath);
		txvFilesList 			= (TextView) findViewById(R.id.txvFilesList);
	}

	public void saveToInternalStorage(View view) {

		String fileName = etFileName.getText().toString();
		String message = etMessage.getText().toString();

		FileOutputStream fos = null;

		try {
			fos = openFileOutput(fileName, MODE_APPEND);
			fos.write(message.getBytes());
			Log.e("TAG", "saveToInternalStorage: " + message.getBytes() );
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void moveToDisplayScreen(View view) {

		Intent intent = new Intent(this, InternalStorageDisplay.class);
		startActivity(intent);
	}

	public void showInternalStoragePath(View view) {

		String dir = String.valueOf(getFilesDir());
		txvInternalStoragePath.setText(dir);
	}

	public void showFilesList(View view) {

		String[] fileList = fileList();
		StringBuilder sb = new StringBuilder();

		for (String file : fileList) {
			sb.append(file + ", ");
		}
		txvFilesList.setText(sb);

	}

	public void deleteFile(View view) {

		boolean isDeleted = deleteFile(etFileToDelete.getText().toString());

		if (isDeleted){
			Toast.makeText(InternalStorageDemo.this,"Successful", Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(InternalStorageDemo.this,"Failed", Toast.LENGTH_LONG).show();
		}
	}
}
