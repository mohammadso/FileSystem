package com.sriyanksiddhartha.filesystemdemo.displayscreens;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sriyanksiddhartha.filesystemdemo.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class InternalStorageDisplay extends AppCompatActivity {

	private EditText etFileName;
	private TextView txvFileContent;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.internal_storage_display);

		etFileName = (EditText) findViewById(R.id.etFileName);
		txvFileContent = (TextView) findViewById(R.id.txvFileContent);
	}

	public void readAndDisplayFromFile(View view) {

		/*
		* File Content: "Hello from my file"
		* How Data is Actually Saved: 0110 0011 1001 1100 1111 -1
		* */

		String fileName = etFileName.getText().toString();
		FileInputStream fis = null;
		StringBuilder sb = new StringBuilder();
		try {
			fis = openFileInput(fileName);
			int read;
			while ((read = fis.read()) != -1)
				sb.append(read);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
