package com.sriyanksiddhartha.filesystemdemo.storageoptions;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sriyanksiddhartha.filesystemdemo.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CacheStorageDemo extends AppCompatActivity {

	private EditText etInternalCacheData, etExternalCacheData;
	private TextView txvInternalCacheData, txvExternalCacheData;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cache_storage);

		etInternalCacheData = (EditText) findViewById(R.id.etInternalCacheData);
		etExternalCacheData = (EditText) findViewById(R.id.etExternalCacheData);

		txvInternalCacheData = (TextView) findViewById(R.id.txvInternalCacheContent);
		txvExternalCacheData = (TextView) findViewById(R.id.txvExternalCacheContent);
	}

	public void saveToInternalCache(View view) {

		File cacheDir = getCacheDir();
		File myCacheFile = new File(cacheDir, "internalCache.txt");
		writeToFile(myCacheFile, etInternalCacheData.getText().toString());
	}

	public void saveToExternalCache(View view) {

		File cacheDir = getExternalCacheDir();
		File myCacheFile = new File(cacheDir, "ExternalCache.txt");
		writeToFile(myCacheFile, etExternalCacheData.getText().toString());
	}

	public void loadFromInternalCache(View view) {

		File cacheDir = getCacheDir();
		File myCacheFile = new File(cacheDir, "internalCache.txt");

		String result = readFromFile(myCacheFile);
		txvInternalCacheData.setText(result);
	}

	public void loadFromExternalCache(View view) {
		File cacheDir = getExternalCacheDir();
		File myCacheFile = new File(cacheDir, "ExternalCache.txt");

		String result = readFromFile(myCacheFile);
		txvExternalCacheData.setText(result);
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
}
