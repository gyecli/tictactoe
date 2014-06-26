*************************************************************************************
*************************************************************************************

CS 349 
A4 -- Android Tic Tac Toe (MVC)

By: Guotain Ye 
2013/07/29
*************************************************************************************


Instructions:

1. The game has two gaming modes: "Player VS. Player" and "Player VS. Computer". 
   The default mode is "Player VS. Player" mode (selected on first screen)

2. 	Before each game, player needs to choose which side (X or O) to start first and 
	optionally enters names 

3. To start a new game, press button <New game>, the scores will accumulate

*************************************************************************************

Coding Structure:

1.  The launcher screen is from "MainActivity.java" corresponding to "activity_main.xml"
2. 	Depending on which mode, single/two player(s) mode, user selects, second screen 
	(SetupView1 / SetupView2) will show up
3. 	Then GameActivity screen shows up

*************************************************************************************

Enhancements:

1. From the launching screen, player(s) can select a "Single/Two player" mode. 
2. Players can optionally enter a short name (max length of 5 characters). 
3. There is a gaming history recording past game records

*************************************************************************************

Notes:
1.	The "options" button and the "back" buttons are not yet implemented