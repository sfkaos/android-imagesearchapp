package com.winraguini.gridimagesearch;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class SearchActivity extends Activity {
	EditText etQuery;
	GridView gvResults;
	Button btnSearch;
	ArrayList<ImageResult> imageResults = new ArrayList<ImageResult>();
	ImageResultArrayAdapter imageAdapter;
	ImageFilter currentFilter;
	String imageQueryString;
	ImageQuery queryUrl;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		this.queryUrl = new ImageQuery("https://ajax.googleapis.com/ajax/services/search/images?rsz=8&v=1.0");
		setupViews();
		imageAdapter = new ImageResultArrayAdapter(this, imageResults);
		gvResults.setAdapter(imageAdapter);
		gvResults.setOnItemClickListener(new OnItemClickListener() {		

			@Override
			public void onItemClick(AdapterView<?> adapter, View parent, int position, long arg3) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), ImageDisplayActivity.class);
				ImageResult imageResult = imageResults.get(position);
				i.putExtra("result", imageResult);
				startActivity(i);
			}
		});
		
		gvResults.setOnScrollListener(new EndlessScrollListener() {
	        @Override
	        public void onLoadMore(int page, int totalItemsCount) {
	                // Triggered only when new data needs to be appended to the list
	                // Add whatever code is needed to append new items to your AdapterView
	            customLoadMoreDataFromApi(totalItemsCount);  
	            Log.d("DEBUG", "totalItemsCount " + totalItemsCount);
	        }
	     });
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}
	
	public void setupViews() {
		etQuery = (EditText) findViewById(R.id.etQuery);
		gvResults = (GridView) findViewById(R.id.gvResults);
		btnSearch = (Button) findViewById(R.id.btnSearch);
	}  
	
	public void onImageSearch(View v) {
		String query = etQuery.getText().toString();		
		if (!query.isEmpty()) {
			imageResults.clear();
			Toast.makeText(this, "Searching for " + query, Toast.LENGTH_SHORT).show();									
			this.queryUrl.setQuery(query);
			this.customLoadMoreDataFromApi(0);
		} else {
			Toast.makeText(this, "Please enter a query string", Toast.LENGTH_SHORT).show();
		}

	}
	
	public void onSettingsAction(MenuItem mi) {
		// handle click here		
		Intent i = new Intent(this, SettingsActivity.class);
		i.putExtra(ImageFilter.FILTER_KEY, currentFilter);
		startActivityForResult(i, 1);				
	}
	
	private void applyFilter() {
		this.queryUrl.setFilter(currentFilter);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		  // REQUEST_CODE is defined above
		  if (resultCode == RESULT_OK && requestCode == 1) {
			  currentFilter = (ImageFilter) data.getSerializableExtra(ImageFilter.FILTER_KEY);
			  this.applyFilter();
		  }
	}
	
	public void customLoadMoreDataFromApi(int offset) {
	      // This method probably sends out a network request and appends new data items to your adapter. 
	      // Use the offset value and add it as a parameter to your API request to retrieve paginated data.
	      // Deserialize API response and then construct new objects to append to the adapter
		AsyncHttpClient client = new AsyncHttpClient();	
		this.queryUrl.setStart(offset);
		client.get(this.queryUrl.getUrl(), 
				new JsonHttpResponseHandler() {
			public void onSuccess(JSONObject response) {
				JSONArray imageJsonResults = null;
				try { 
					imageJsonResults = response.getJSONObject("responseData").getJSONArray("results");
					imageAdapter.addAll(ImageResult.fromJSONArray(imageJsonResults));
					//Log.d("DEBUG", imageResults.toString());
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

}
