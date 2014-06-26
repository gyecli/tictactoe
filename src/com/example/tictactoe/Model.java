package com.example.tictactoe;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class Model extends Observable {	
	private int current_player = 0; 	// 1 means O, 2 means X
	private int restart = 1; 			// 0 means the start of the game, 1 means has started
	private int[] game = new int[9];	// 0 for empty, 1 for one player, 2 for the other
	private int action = 0; 			// 1 means get action from the board, 0 otherwise
	private int winner = 0; 			// 1 means player 1 wins, 2 means the other 
	private int moves = 0; 
	private int illegal = 0; 			// indicate an illegal move
	private int previous_step = 0;		// indicate previous section of the move, range from [1, 9]		
	
	private int o_scores = 0; 			// scores of player 1
	private int x_scores = 0; 			// scores of player 2
	private int draw_scores = 0; 		// scores of draw
	private int draw = 0; 				// indicate it's a draw game
	private int ai = 0; 				// 1 indicates an AI mode, 0 otherwise
	
	private String x_name = " "; 				// name for player X
	private String o_name = " "; 				// name for player O
	
	private int[] winList = new int[3];
	
	Model() {
		initList(); 
		setChanged();
	}
	// notify all the observers
	public void signal() {
		setChanged();
		notifyObservers();
	}
	
	public void initList() {
		for (int i = 0; i < 9; i++) {
			game[i] = 0; 
		}
	}
	
	public int getIllegal() {
		return illegal; 
	}
	public void setIllegal(int i) {
		illegal = i; 
	}
	
	public int getMoves() {
		return moves; 
	}
	public void setMoves(int m) {
		moves = m; 
	}
	
	public void incrementMoves() {
		this.restart = 0; 
		this.moves++; 
		if (moves == 9 && winner == 0) {
			action = 0; 
			draw = 1; 
			updateScores(1, 0); 
		}
		signal();				// notify all observers
	}
	
	public int getRestart() {
		return restart; 
	}
	
	public void setRestart(int s) {
		this.restart = s; 
		signal();				// notify all observers
	}
	
	public int getCurrentPlayer() {
		return current_player; 
	}
	
	public void setCurrentPlayer(int player) {
		this.current_player = player; 
		signal();				// notify all observers
	}
	public void setFirstPlayer(int player) {
		this.current_player = player; 
	}
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action; 
	}
	
	public int[] getStatus() {
		return game; 
	}
	
	public int getPrevious() {
		return previous_step;
	}
	
	public int getOscores() {
		return o_scores;
	}
	public void setOscores(int os){
		o_scores = os; 
	}
	public int getXscores() {
		return x_scores;
	}
	public void setXscores(int xs) {
		x_scores = xs; 
	}
	public int getDrawScores() {
		return draw_scores; 
	}
	public void setDrawScores(int s) {
		draw_scores = s; 
	}
	public int getDraw() {
		return draw; 
	}
	public void setDraw(int d) {
		draw = d;
	}
	public int getPreviousStep() {
		return previous_step;
	}
	public void setPreviousStep(int p) {
		previous_step = p; 
	}
	public void updateScores(int draw, int winner) {
		if (winner == 1) {
			o_scores++; 
		} else if (winner == 2) {
			x_scores++; 
		} else {
			//  no winner, do nothing
		}
		draw_scores = draw_scores + draw; 
	}
	
	
	// place value "player" into game[step] 
	public int setStatus(int step, int player) {
		
		if (game[step-1] != 0) {
			System.out.println("Model: Illegal step " + step);
			illegal = 1; 
			action = 0; 	// indicate a invalid move
			signal();				// notify all observers
			return 0;
		} else {
			System.out.println("Model: legal step " + step);
			action = 1; 	// indicate a valid move
			illegal = 0; 
			game[step-1] = player; 
			previous_step = step;
			
			if(win(player) == true) {
				return 2; 
			}
			signal();				// notify all observers
			return 1;
		}
	}
	
	public int getWinner() {
		return winner; 
	}
	public void setWinner(int w) {
		winner = w; 
	}
	
	public int[] getWinList() {
		return winList; 
	}
	public int[] getGame() {
		return game; 
	}
	public void setGame(int[] g) {
		for (int i = 0; i < 9; i++) {
			game[i] = g[i]; 
		}
		signal(); 
	}
	public void setWinList(int i, int j, int k) {
		winList[0] = i;
		winList[1] = j;
		winList[2] = k; 
	}
	public void setWL(int[] wl) {
		for (int i = 0; i < 3; i++) {
			winList[i] = wl[i]; 
		}
	}
	
	
	public boolean win(int player) {
		if (game[0] == game [1] && game[1] == game[2] && game[2] == player) {
			System.out.println("Player " + player + " won!");
			winner = player; 
			setWinList(0, 1, 2); 
		} else if (game[3] == game [4] && game[4] == game[5] && game[5] == player) {
			System.out.println("Player " + player + " won!");
			winner = player; 
			setWinList(3, 4, 5); 
		} else if (game[6] == game [7] && game[7] == game[8] && game[8] == player) {
			System.out.println("Player " + player + " won!");
			winner = player; 
			setWinList(6, 7, 8); 
		} else if (game[0] == game [3] && game[3] == game[6] && game[6] == player) {
			System.out.println("Player " + player + " won!");
			winner = player; 
			setWinList(0, 3, 6); 
		} else if (game[1] == game [4] && game[4] == game[7] && game[7] == player) {
			System.out.println("Player " + player + " won!");
			winner = player; 
			setWinList(1, 4, 7); 
		} else if (game[2] == game [5] && game[5] == game[8] && game[8] == player) {
			System.out.println("Player " + player + " won!");
			winner = player; 
			setWinList(2, 5, 8); 
		} else if (game[0] == game [4] && game[4] == game[8] && game[8] == player) {
			System.out.println("Player " + player + " won!");
			winner = player; 
			setWinList(0, 4, 8); 
		} else if (game[2] == game [4] && game[4] == game[6] && game[6] == player) {
			System.out.println("Player " + player + " won!");
			winner = player; 
			setWinList(2, 4, 6); 
		} 
		
		if (winner != 0) {		// we get a winner
			System.out.println("x_scores: " + x_scores);
			updateScores(0, winner);
			signal();				// notify all observers
			return true; 
		} else {
			return false; 
		}	
	}
	
	public void restart () {
		for (int i = 0; i < 9; i++) {
			game[i] = 0; 
		}
		initList(); 
		current_player = 0; 
		action = 0; 
		winner = 0; 
		moves = 0; 
		illegal = 0; 
		previous_step = 0;
		restart = 1;
		signal();				// notify all observers
	}
	
	public int getAI() {
		//TODO: still need signal?
		return ai; 
	}
	public void setAI(int onOff) {
		//TODO: still need signal?
		ai = onOff; 
	}
	public void start_AI() {
		if (ai == 1 && moves == 0) {		// we can change mode only when no moves have been made
			current_player = 0;
			x_scores = 0;					// whenever switch mode, clear scores records
			o_scores = 0;
			draw_scores = 0;
			draw = 0;
			ai = 0;
		} else if (ai == 0 && moves == 0) {
			current_player = 0;
			x_scores = 0;
			o_scores = 0;
			draw_scores = 0;
			draw = 0;
			ai = 1;
		}
		signal();				// notify all observers
	}
	
	// Currently the AI just take random moves
	// Still need to implement a clever algorithm
	public void ai_control(int ai_player) {
		ArrayList<Integer> aiList = new ArrayList<Integer>();
		Random randomGenerator = new Random();
		for (int i = 0; i < 9; i++) {
			if (game[i] == 0) {			// get a list of empty slots
				aiList.add(i);	
			}
		}
		int index = randomGenerator.nextInt(aiList.size());
		int ai_step = aiList.get(index);
		
		action = 1; 	// indicate a valid move
		illegal = 0; 	
		game[ai_step] = ai_player; 
		previous_step = ai_step+1;
		
		signal();
		
		if(win(ai_player) == true) {
			//TODO: 
			System.out.println("Computer Won!");
		}
		
	}
	
	public String get_x_name() {
		return x_name; 
	}
	public void setXname(String name) {
		x_name = name; 
	}
	public String get_o_name() {
		return o_name; 
	}
	public void setOname(String name) {
		o_name = name; 
	}
	public void set_name(String xName, String oName) {
		x_name = xName;
		o_name = oName; 
	}
}
