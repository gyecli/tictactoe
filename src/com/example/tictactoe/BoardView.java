package com.example.tictactoe;

import java.util.Observable;
import java.util.Observer;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

@SuppressLint("ViewConstructor")
public class BoardView extends LinearLayout implements Observer {
	
	private Model model;
	private Button button[];
	
	public int current_player; 
	public int next_player; 
	public int section = 0; 	

	public BoardView(Context context, Model m) {
		super(context);
		
	    Log.d("TicTacToe_BoardView", "View2 constructor");

		// get the xml description of the view and "inflate" it
		// into the display (kind of like rendering it)
		View.inflate(context, R.layout.activity_board_view, this);
		
		// Initiate 9 buttons
		button = new Button[9]; 

		// save the model reference
		model = m;
		// add this view to model's list of observers
		model.addObserver(this);
		
		// assign 9 buttons from xml by reference
		initButton(); 
	}
	
	public void initButton() {
		Log.d("TicTacToe_BoardView", "initButton");
		button[0] = (Button) findViewById(R.id.button1);
		button[1] = (Button) findViewById(R.id.button2);
		button[2] = (Button) findViewById(R.id.button3);
		button[3] = (Button) findViewById(R.id.button4);
		button[4] = (Button) findViewById(R.id.button5);
		button[5] = (Button) findViewById(R.id.button6);
		button[6] = (Button) findViewById(R.id.button7);
		button[7] = (Button) findViewById(R.id.button8);
		button[8] = (Button) findViewById(R.id.button9);
	}
	

	// the model call this to update the view
	public void update(Observable observable, Object data) {
	    Log.d("TicTacToe_BoardView", "update BoardView");
	    //System.out.println("BoardView: updateView");
	   
//	    if(model.getAI() == 1 && model.getCurrentPlayer() == 1 && model.getRestart() == 1) {
//	    	try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				System.out.println(e);
//			} 
//	    }
	    
		if (model.getAction() == 1) {				// if it's a valid move
//			String str; 
//			if (model.getCurrentPlayer() == 1) {
//				str = "X";
//			} else if (model.getCurrentPlayer()== 2) {
//				str = "O"; 
//			} else {
//				str = " ";
//			}
//			button[model.getPrevious()-1].setText(str); 
			
			int[] gameList = model.getGame(); 
			String str; 
			for (int i = 0; i < 9; i++) {
				if (gameList[i] == 1) {
					str = "O";
				} else if (gameList[i] == 2) {
					str = "X";
				} else {
					str = " ";
				}
				button[i].setText(str);
			}
			
			if (model.getWinner() != 0) {
				// Got a winner
				int[] winL = new int[3];
				winL = model.getWinList();
				
				for (int i = 0; i < 3; i++) {
					button[winL[i]].setTextColor(Color.parseColor("#FF0000"));
				}
			}
		}
		
		if (model.getRestart() == 1) {
			// start of the game, reset buttons' color
			for (int i = 0; i < 9; i++) 
			{
				button[i].setTextColor(Color.BLACK);
			}
		}
		
		//TODO: might get rid of this
		if (model.getCurrentPlayer() == 0) {
			// start or restart
			for(int i = 0; i < 9; i++) {
				button[i].setText(" ");
			}
		}
	}
	
	
}
