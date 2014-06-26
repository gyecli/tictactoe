package com.example.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class GameActivity extends Activity {
	Model model;
		
	public int current_player; 
	public int next_player; 
	public int section = 0; 
	public int mode;			// 1 means PlayerVS.Plyer mode, 2 means PlayerVS.Comp mode
	
	public final static String EXTRA_COMMON_KEY = "com.example.tictactoe.COMMON_KEY";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("TicTacToe_GameActivity", "onCreate");

		// load the base UI (has places for the views)
		setContentView(R.layout.gameactivity);
		
		// Setup model
		model = new Model();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		Log.d("TicTacToe_GameActivity", "onPostCreate");
		super.onPostCreate(savedInstanceState);

		// can only get widgets by id in onPostCreate for activity xml res
		// create the views and add them to the game activity
		ToolBarView view1 = new ToolBarView(this, model);
		ViewGroup v1 = (ViewGroup) findViewById(R.id.gameactivity_1);
		v1.addView(view1);

		BoardView view2 = new BoardView(this, model);
		ViewGroup v2 = (ViewGroup) findViewById(R.id.gameactivity_2);
		v2.addView(view2);

		PlayerView view3 = new PlayerView(this, model);
		ViewGroup v3 = (ViewGroup) findViewById(R.id.gameactivity_3);
		v3.addView(view3);	
			
		Bundle extras = getIntent().getExtras();
		int first_player;
		String x_name;
		String o_name;
		
		if (extras.getInt(EXTRA_COMMON_KEY) == 1) {
			// Intent fired by Setup Activity 1 -- single player mode
			first_player = extras.getInt(SetupView1.EXTRA_CURRENT_PLAYER);
			x_name = extras.getString(SetupView1.EXTRA_PLAYER);
			o_name = extras.getString(SetupView1.EXTRA_COMPUTER);
			
			model.setAI(1);
		} else {
			// Intent fired by Setup Activity 2 -- two players mode
			first_player = extras.getInt(SetupView1.EXTRA_CURRENT_PLAYER);
			x_name = extras.getString(SetupView2.EXTRA_PLAYER1);
			o_name = extras.getString(SetupView2.EXTRA_PLAYER2);
			
			model.setAI(0);
		}
		
		model.set_name(x_name, o_name);
		model.setCurrentPlayer(first_player);
		
		if (first_player == 1 && model.getAI() == 1) {
			// AI mode is ON, and it's computer's turn 
			
			model.ai_control(first_player);
			model.incrementMoves();
			model.setCurrentPlayer(2);
		}		
	}

	// save and restore state (need to do this to support orientation change)
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		Log.d("TicTacToe_GameActivity", "save state");
//		outState.putInt("Counter", model.getCounterValue());
		saveModel(outState); 
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		Log.d("TicTacToe_GameActivity", "restore state");
		super.onRestoreInstanceState(savedInstanceState);
		restoreModel(savedInstanceState, model);
		//model.setCounterValue(savedInstanceState.getInt("Counter"));
	}
	
	
	public void goBack(View view) {
		Log.d("TicTacToe_GameActivity", "goBack");
		//TODO:
	}

	public void newGame(View view) {
		Log.d("TicTacToe_GameActivity", "newGame");
		int current_player = model.getCurrentPlayer(); 	// TODO: still the the same player starts first? 
		model.restart();
		model.setCurrentPlayer(current_player); 
	}
	
	public void placeStep(View view) {
		Log.d("TicTacToe_GameActivity", "placeStep");
		
		if (model.getWinner() == 0) {
		switch (view.getId()) {
			case R.id.button1:
				action(model, 1);
				break;
			case R.id.button2:
				action(model, 2);
				break;
			case R.id.button3:
				action(model, 3);
				break;
			case R.id.button4:
				action(model, 4);
				break;
			case R.id.button5:
				action(model, 5);
				break;
			case R.id.button6:
				action(model, 6);
				break;
			case R.id.button7:
				action(model, 7);
				break;
			case R.id.button8:
				action(model, 8);
				break;
			case R.id.button9:
				action(model, 9);
				break;   
		}
		}
		if (model.getAI() == 1 && model.getWinner() == 0) {
			// it's computer's turn to place a step
			computer(); 
		}
	}

	// action from button pressed
	public void action(Model model, int step) {
		Log.d("TicTacToe_GameActivity", "action");
		int current_player = model.getCurrentPlayer(); 
		if (current_player == 1) {
			next_player = 2; 
		} else if (current_player == 2){
			next_player = 1; 
		} 
		
		System.out.println("BoardView: action: ");
		// flag == 1 indicates it's ready to place a move
		if (model.getWinner() == 0) {
			section = step; 
			// setStatus() places current_player into game[step], 
			//             and set "action" into 1, indicating a valid move
			if (model.setStatus(step, current_player) != 0) {	
				model.incrementMoves();
				model.setCurrentPlayer(next_player);
			}
		}
	}
	
	// hand control to AI
	public void computer() {
		// AI mode is on and computer go first
		Log.d("TicTacToe_GameActivity", "computer");
		
		model.ai_control(1);		// 1 means player O, 2 means player X
		model.incrementMoves();
		model.setCurrentPlayer(2);
	}
	
	// For switching between "landscape" and "portrait", 
	// save the data of current mode into bundle
	public void saveModel(Bundle B){
		B.putInt("current_player", model.getCurrentPlayer());
		B.putInt("restart", model.getRestart());
		B.putIntArray("game", model.getGame());
		B.putInt("action", model.getAction());
		B.putInt("winner", model.getWinner());
		B.putInt("moves", model.getMoves());
		B.putInt("illegal", model.getIllegal());
		B.putInt("previous_step", model.getPreviousStep());
		B.putInt("x_scores", model.getXscores());
		B.putInt("o_scores", model.getOscores());
		B.putInt("draw_scores", model.getDrawScores());
		B.putInt("draw", model.getDraw());
		B.putInt("ai", model.getAI());
		B.putInt("action", model.getAction());
		B.putString("x_name", model.get_x_name());
		B.putString("o_name", model.get_o_name());
		B.putIntArray("winList", model.getWinList());
	}
	
	// retrive data from bundle after a switch between landscape and portrait
	public void restoreModel(Bundle savedInstanceState, Model m) {
		m.setCurrentPlayer(savedInstanceState.getInt("current_player"));
		m.setRestart(savedInstanceState.getInt("restart"));	
		m.setGame(savedInstanceState.getIntArray("game"));
		m.setAction(savedInstanceState.getInt("action"));
		m.setWinner(savedInstanceState.getInt("winner"));
		m.setMoves(savedInstanceState.getInt("moves"));
		m.setIllegal(savedInstanceState.getInt("illegal"));
		m.setPreviousStep(savedInstanceState.getInt("previous_step"));
		m.setXscores(savedInstanceState.getInt("x_scores"));
		m.setOscores(savedInstanceState.getInt("o_scores"));
		m.setDrawScores(savedInstanceState.getInt("draw_scores"));
		m.setDraw(savedInstanceState.getInt("draw"));
		m.setAI(savedInstanceState.getInt("ai"));
		m.setAction(savedInstanceState.getInt("action"));
		m.setXname(savedInstanceState.getString("x_name"));
		m.setOname(savedInstanceState.getString("o_name"));
		m.setWL(savedInstanceState.getIntArray("winList"));
	}
}
