package com.example.tictactoe;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class SetupView2 extends Activity {
	private Bundle extras = new Bundle();
	private int flag = 0; 	// 0 means haven't selected which first
	
	public final static String EXTRA_COMMON_KEY = "com.example.tictactoe.COMMON_KEY";		// indicate AI_mode
	public final static String EXTRA_CURRENT_PLAYER = "com.example.tictactoe.CURRENT_PLAYER";
	public final static String EXTRA_PLAYER1 = "com.example.tictactoe.PLAYER1";				// for player1's name
	public final static String EXTRA_PLAYER2 = "com.example.tictactoe.PLAYER2";				// for player2's name
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("TicTacToe-SetupView2", "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup_view2);
		// Show the Up button in the action bar.
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Log.d("TicTacToe-SetupView2", "onCreateOptionsMenu");
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setup_view2, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.d("TicTacToe-SetupView2", "onOptionItemSelected");
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void x_first(View view) {
		Log.d("TicTacToe-SetupView2", "x_first");
		flag = 1; 
		extras.putInt(EXTRA_CURRENT_PLAYER, 2);
	}
	
	public void o_first(View view) {
		Log.d("TicTacToe-SetupView2", "o_first");
		flag = 1; 
		extras.putInt(EXTRA_CURRENT_PLAYER, 1);
	}
	
	public void startGame(View view) {
		Log.d("TicTacToe-SetupView2", "startGame");
		
		if (flag == 1) {
		//TODO: do I still need this, since data already stored in model
		Intent intent = new Intent(this, GameActivity.class);
		
		EditText player_name = (EditText) findViewById(R.id.x_name_field2);
		EditText comp_name = (EditText) findViewById(R.id.o_name_field2);
		String p_name = player_name.getText().toString();
		String c_name = comp_name.getText().toString();
		
		extras.putString(EXTRA_PLAYER1, p_name);
		extras.putString(EXTRA_PLAYER2, c_name);
		extras.putInt(EXTRA_COMMON_KEY, 2);
		intent.putExtras(extras);
		
		startActivity(intent);
		} else {
			// TODO: raise an alert -- haven't selected which first
			
		}
	}
	
	//TODO: 
	public void goback(View view) {
		Log.d("TicTacToe-SetupView2", "goback");
	}
}
