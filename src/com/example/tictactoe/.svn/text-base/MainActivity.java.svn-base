package com.example.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {
	
	public final static String EXTRA_MODE = "com.example.tictactoe.MODE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("TicTacToe_MainActivity", "onCreate");

		// load the base UI (has places for the views)
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		Log.d("TicTacToe_MainActivity", "onPostCreate");
	}

	// save and restore state (need to do this to support orientation change)
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		Log.d("TicTacToe_MainActivity", "save state");
		//outState.putInt("Counter", model.getCounterValue());
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		Log.d("TicTacToe_MainActivity", "restore state");
		super.onRestoreInstanceState(savedInstanceState);
		//model.setCounterValue(savedInstanceState.getInt("Counter"));
	}
	
	public void select_one(View view) {
		Log.d("TicTacToe_MainActivity", "select_one");
		
		Intent intent = new Intent(this, SetupView1.class);
		//intent.putExtra(EXTRA_MODE, ai_mode);
		startActivity(intent);
		
	}
	
	public void select_two(View view) {
		Log.d("TicTacToe_MainActivity", "select_two");
		Intent intent = new Intent(this, SetupView2.class);
		startActivity(intent);
	}
	
	public void select_options(View view) {
		Log.d("TicTacToe_MainActivity", "select_options");
	}
	
	public void select_exit(View view) {
		Log.d("TicTacToe_MainActivity", "select_exit");
		System.exit(0);; 
	}
}

