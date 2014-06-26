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
public class PlayerView extends LinearLayout implements Observer {
	
	private Model model;
	//private TextView textview;

	public PlayerView(Context context, Model m) {
		super(context);
		
	    Log.d("TicTacToe_PlayerView", "PlayerView constructor");

		// get the xml description of the view and "inflate" it
		// into the display (kind of like rendering it)
		View.inflate(context, R.layout.activity_player_view, this);

		// save the model reference
		model = m;
		// add this view to model's list of observers
		model.addObserver(this);
		
	}

	// the model call this to update the view
	public void update(Observable observable, Object data) {
	    Log.d("TicTacToe_PlayerView", "update PlayerView");
	    //System.out.println("PlayerView: updateView");
		
	    if (model.getMoves() == 0) 
	    {
	    	TextView o_name = (TextView) findViewById(R.id.oName);
	    	TextView x_name = (TextView) findViewById(R.id.xName);
	    	o_name.setText(model.get_o_name() + "(O)");
	    	x_name.setText(model.get_x_name() + "(X)");
	    }
	    
		TextView xScore = (TextView) findViewById(R.id.xScore);
		TextView oScore = (TextView) findViewById(R.id.oScore);
		TextView tieScore = (TextView) findViewById(R.id.tieScore);
		xScore.setText(Integer.toString(model.getXscores()));
		oScore.setText(Integer.toString(model.getOscores()));
		tieScore.setText(Integer.toString(model.getDraw()));
	}
}
