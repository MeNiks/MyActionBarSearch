package com.niks.actionbarsearch;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.database.MatrixCursor;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

public class MainActivity extends Activity   {

 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

		setContentView(R.layout.main);
		 
		items=new ArrayList<String>();
		items.add("First");
		items.add("SEc");
		items.add("Third");
		items.add("Fourth");
		items.add("First");

	}

	 

	private List<String> items;

	private Menu menu;

	@Override
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public boolean onCreateOptionsMenu(Menu menu) {

	    getMenuInflater().inflate(R.menu.wensitelist, menu);

	    this.menu = menu;

	    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

	        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

	        SearchView search = (SearchView) menu.findItem(R.id.action_search).getActionView();

	        search.setSearchableInfo(manager.getSearchableInfo(getComponentName()));

	        search.setOnQueryTextListener(new OnQueryTextListener() { 

	            @Override 
	            public boolean onQueryTextChange(String query) {

	                loadHistory(query);

	                return true; 

	            }

				@Override
				public boolean onQueryTextSubmit(String arg0) {
					// TODO Auto-generated method stub
					return false;
				} 

	        });

	    }

	    return true;

	}

	// History
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void loadHistory(String query) {

	    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

	        // Cursor
	        String[] columns = new String[] { "_id", "text" };
	        Object[] temp = new Object[] { 0, "default" };

	        MatrixCursor cursor = new MatrixCursor(columns);

	        for(int i = 0; i < items.size(); i++) {

	            temp[0] = i;
	            temp[1] = items.get(i);//replaced s with i as s not used anywhere.

	            cursor.addRow(temp);

	        }

	        // SearchView
	        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

	        final SearchView search = (SearchView) menu.findItem(R.id.action_search).getActionView();

	        search.setSuggestionsAdapter(new ExampleAdapter(this, cursor, items));

	    }

	}


}