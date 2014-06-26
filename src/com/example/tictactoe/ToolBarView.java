package com.example.tictactoe;

import java.util.Observable;
import java.util.Observer;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("ViewConstructor")
public class ToolBarView extends LinearLayout implements Observer {
	
	private Model model;

	public ToolBarView(Context context, Model m) {
		super(context);
		
	    Log.d("TicTacToe_ToolBarView", "ToolBarView constructor");
		
		// get the xml description of the view and "inflate" it
		// into the display (kind of like rendering it)
		View.inflate(context, R.layout.activity_tool_bar_view, this);

		// save the model reference
		model = m;
		// add this view to model's list of observers
		model.addObserver(this);
	}
	
	// the model call this to update the view
	public void update(Observable observable, Object data) {
	    Log.d("TicTacToe_ToolBarView", "update ToolBarView");
		
	    TextView moves = (TextView) findViewById(R.id.moves);
	    TextView numMoves = (TextView) findViewById(R.id.num_moves);
	    TextView turn = (TextView) findViewById(R.id.turn);
	    
		if (model.getMoves() != 0 && model.getIllegal() == 0) 
		{
			numMoves.setText("moves: ");
			moves.setText(Integer.toString(model.getMoves()));
		}
		
		if(model.getCurrentPlayer() == 1) 
		{
			//System.out.println("ToolbarView: updateView " + model.getCurrentPlayer() + "OOOOOO");
			turn.setText("It's O's turn");
		} else {
			//System.out.println("ToolbarView: updateView " + model.getCurrentPlayer() + "XXXXXX");
			turn.setText("It's X's turn");
		}
		
		// TODO: use real players' names
		// we get a winner
		if (model.getWinner() != 0) {
			int winner = model.getWinner(); 
			if (winner == 1) {
				String o_name = model.get_o_name();
				numMoves.setText(o_name);
				moves.setText("(O) won!");
			} else {
				String x_name = model.get_x_name();
				numMoves.setText(x_name);
				moves.setText("(X) won!");
			}
		} 
		
		// Game draws, no winner
		if(model.getWinner() == 0 && model.getMoves() == 9) {
			numMoves.setText("Game Drawn");
		}
	}
}
