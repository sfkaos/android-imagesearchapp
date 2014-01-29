package com.winraguini.gridimagesearch;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class SettingsActivity extends Activity {	
	Spinner spSize;
	Spinner spColor;
	Spinner spType;	
	EditText etSite;
	
	ArrayList<String> sizeFilterArray;
	ArrayList<String> colorFilterArray;
	ArrayList<String> typeFilterArray;
	
	private String imgSize;
	private String imgColor;
	private String imgType;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		ImageView logo = (ImageView) findViewById(android.R.id.home);
		logo.setImageDrawable(getResources().getDrawable(R.drawable.ic_settings));		
		setupSpinners();	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}
	
	public void setupSpinners() {
		etSite = (EditText) findViewById(R.id.etSite);
		
		sizeFilterArray = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.size_spinner_items)));
		colorFilterArray = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.color_spinner_items)));
		typeFilterArray = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.type_spinner_items)));
		
		spSize = (Spinner) findViewById(R.id.spSize);
		spSize.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {				
				String sizeValue = (String) parentView.getItemAtPosition(position);				
				
				if (position > 0) {
					imgSize = sizeValue;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		spColor = (Spinner) findViewById(R.id.spColor);
		spColor.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {				
				String colorValue = (String) parentView.getItemAtPosition(position);								
				if (position > 0) {
					imgColor = colorValue;					
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		spType = (Spinner) findViewById(R.id.spType);
		spType.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {				
				String typeValue = (String) parentView.getItemAtPosition(position);				
				
				if (position > 0) {
					imgType = typeValue;					
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		ImageFilter imageFilter = (ImageFilter) getIntent().getSerializableExtra(ImageFilter.FILTER_KEY);
		//Setup the current filters
		if (imageFilter != null) {			
			spSize.setSelection(sizeFilterArray.indexOf(imageFilter.getImgSize()));
			spColor.setSelection(colorFilterArray.indexOf(imageFilter.getImgColor()));
			spType.setSelection(typeFilterArray.indexOf(imageFilter.getImgType()));						
			etSite.setText(imageFilter.getImgSite());			
		} 
	}
	
	public ImageFilter populateFilter() {
		ImageFilter filter = new ImageFilter(imgSize, imgColor, imgType, etSite.getText().toString());		
		return filter;
	}
	
	public void onUpdateAction(View v) {
		Intent data = new Intent();		
		ImageFilter filter = this.populateFilter();		
		data.putExtra(ImageFilter.FILTER_KEY, filter);		
		setResult(RESULT_OK, data);		
		finish();
	}

}
