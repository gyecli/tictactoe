package com.example.tictactoe;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class SetupView1 extends Activity {
	private Bundle extras = new Bundle();
	private int flag = 0; 		// used to indicate one of "X first" and "O first" get selected.  0 means none selected
	
	public final static String EXTRA_COMMON_KEY = "com.example.tictactoe.COMMON_KEY";	// indicate AI_MODE
	public final static String EXTRA_CURRENT_PLAYER = "com.example.tictactoe.CURRENT_PLAYER";
	public final static String EXTRA_PLAYER = "com.example.tictactoe.PLAYER";		// for player name
	public final static String EXTRA_COMPUTER = "com.example.tictactoe.COMPUTER";	// for computer name
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("TicTacToe_SetupView1", "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup_view1);
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
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setup_view1, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
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
		flag = 1;
		Log.d("TicTacToe_SetupView1", "x_first");
		extras.putInt(EXTRA_CURRENT_PLAYER, 2);
	}
	
	public void o_first(View view) {
		flag = 1; 
		Log.d("TicTacToe-SetupView1", "o_first");
		extras.putInt(EXTRA_CURRENT_PLAYER, 1);
	}
	
	public void startGame(View view) {
		Log.d("TicTacToe-SetupView1", "startGame");
		
		if (flag == 1) {
			//TODO: do I still need this, since data already stored in model?
			Intent intent = new Intent(this, GameActivity.class);
		
			EditText player_name = (EditText) findViewById(R.id.x_name_field1);
			EditText comp_name = (EditText) findViewById(R.id.o_name_field1);
			String p_name = player_name.getText().toString();
			String c_name = comp_name.getText().toString();
			extras.putString(EXTRA_PLAYER, p_name);
			extras.putString(EXTRA_COMPUTER, c_name);
		
			extras.putInt(EXTRA_COMMON_KEY, 1);			// used to indicate AI mode
			intent.putExtras(extras);
		
			startActivity(intent);
		} else {
			// TODO: cause an alert -- haven't picked which first
//			// 1. Instantiate an AlertDialog.Builder with its constructor
//			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	//
//			// 2. Chain together various setter methods to set the dialog characteristics
//			builder.setMessage(R.string.dialog_message)
//			       .setTitle(R.string.dialog_title);
	//
//			// 3. Get the AlertDialog from create()
//			AlertDialog dialog = builder.create();
		}

	}
	
	//TODO: 
	public void goback(View view) {
		Log.d("TicTacToe-SetupView1", "goback");
	}
}
