package com.sriyanksiddhartha.filesystemdemo.storageoptions;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sriyanksiddhartha.filesystemdemo.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class ExternalStorageDemo extends AppCompatActivity {

	private EditText etExternalPrivate, etExternalPublic;
	private TextView txvExternalPrivate, txvExternalPublic;
	private LinearLayout linearLayout;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.external_storage);

		linearLayout = (LinearLayout) findViewById(R.id.activity_main);

		etExternalPrivate = (EditText) findViewById(R.id.etExternalPrivate);
		etExternalPublic = (EditText) findViewById(R.id.etExternalPublic);

		txvExternalPrivate = (TextView) findViewById(R.id.txvExternalPrivate);
		txvExternalPublic = (TextView) findViewById(R.id.txvExternalPublic);
////////////////////////////////////////////////////////////////////
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_UNMOUNTED)){
			Toast.makeText(this, state, Toast.LENGTH_SHORT).show();
			linearLayout.setVisibility(View.INVISIBLE);
		}
////////////////////////////////////////////////////////////////////

	}

	public void saveToExternalPrivate(View view) {

		if (isExternalStorageWritable()){

			String data = etExternalPrivate.getText().toString();
			File dir = getExternalFilesDir(null);
			File file = new File(dir, "MyExternalPrivate.txt");
			writeToFile(file, data);

		} else {
			Toast.makeText(this, "External Storage Not Available", Toast.LENGTH_LONG).show();
		}

	}

	public void saveToExternalPublic(View view) {

		if (isExternalStorageWritable()){

			String data = etExternalPublic.getText().toString();
			File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			File file = new File(dir, "MyExternalPublic.txt");
			writeToFile(file, data);
		} else {
			Toast.makeText(this, "External Storage Not Available", Toast.LENGTH_LONG).show();
		}

	}

	public void loadFromExternalPrivate(View view) {

		if (isExternalStorageReadable()){

			File file = new File(getExternalFilesDir(null), "MyExternalPrivate.txt");
			txvExternalPrivate.setText(readFromFile(file));

		} else {
			Toast.makeText(this, "External Storage Not Available", Toast.LENGTH_LONG).show();
		}

	}

	public void loadFromExternalPublic(View view) {

		if (isExternalStorageReadable()){

			File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "MyExternalPublic.txt");
			txvExternalPublic.setText(readFromFile(file));

		} else {
			Toast.makeText(this, "External Storage Not Available", Toast.LENGTH_LONG).show();
		}

	}

	private void writeToFile(File file, String string){

		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(string.getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileOutputStream != null){
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private String readFromFile(File file){

		FileInputStream fileInputStream = null;
		StringBuilder sb = new StringBuilder();

		try {
			fileInputStream = new FileInputStream(file);
			int read;
			while ((read = fileInputStream.read()) != -1){
				sb.append((char) read);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileInputStream != null){
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}

	public boolean isExternalStorageWritable(){

		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)){
			return true;
		}
		return false;
	}

	public boolean isExternalStorageReadable(){

		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED) || state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
			return true;
		}
		return false;
	}


}
