package com.example.tictactoe;

import java.util.Random;

public class Game {
	public static final int SIZE = 9;
	public static final char HUMAN_PLAYER = 'X';
	public static final char COMPUTER_PLAYER = 'O';
	public static final char NOTHING = ' ';
	
	private Random mrand;
	
	private char mBoard[];
	
	public Game(){
		mBoard = new char [SIZE];
		mrand = new Random();
	}
	
	public void clearBoard(){
		for(int i = 0;i<SIZE;i++){
			mBoard[i] = NOTHING;
		}
	}
	
	public void setMove(char player,int location){
		if(location>=0 && location<SIZE && mBoard[location]==NOTHING){
			mBoard[location] = player;
		}
	}
	/**
	 * Check for a winner.  Return a status value indicating the board status.
	 * @return Return 0 if no winner or tie yet, 1 if it's a tie, 2 if X won,
	 * or 3 if O won.
	 */
	
	public int checkForWinner(){
		for (int i = 0; i <= 6; i += 3) {
			if (mBoard[i] == HUMAN_PLAYER && 
				mBoard[i+1] == HUMAN_PLAYER &&
				mBoard[i+2]== HUMAN_PLAYER)
				return 2;
			if (mBoard[i] == COMPUTER_PLAYER && 
				mBoard[i+1]== COMPUTER_PLAYER && 
				mBoard[i+2] == COMPUTER_PLAYER)
				return 3;
		}
	
		// Check vertical wins
		for (int i = 0; i <= 2; i++) {
			if (mBoard[i] == HUMAN_PLAYER && 
				mBoard[i+3] == HUMAN_PLAYER && 
				mBoard[i+6]== HUMAN_PLAYER)
				return 2;
			if (mBoard[i] == COMPUTER_PLAYER && 
				mBoard[i+3] == COMPUTER_PLAYER && 
				mBoard[i+6]== COMPUTER_PLAYER)
				return 3;
		}
	
		// Check for diagonal wins
		if ((mBoard[0] == HUMAN_PLAYER &&
			 mBoard[4] == HUMAN_PLAYER && 
			 mBoard[8] == HUMAN_PLAYER) ||
			(mBoard[2] == HUMAN_PLAYER && 
			 mBoard[4] == HUMAN_PLAYER &&
			 mBoard[6] == HUMAN_PLAYER))
			return 2;
		if ((mBoard[0] == COMPUTER_PLAYER &&
			 mBoard[4] == COMPUTER_PLAYER && 
			 mBoard[8] == COMPUTER_PLAYER) ||
			(mBoard[2] == COMPUTER_PLAYER && 
			 mBoard[4] == COMPUTER_PLAYER &&
			 mBoard[6] == COMPUTER_PLAYER))
			return 3;
	
		// Check for tie
		for (int i = 0; i < SIZE; i++) {
			// If we find a number, then no one has won yet
			if (mBoard[i] != HUMAN_PLAYER && mBoard[i] != COMPUTER_PLAYER)
				return 0;
		}
	
		// If we make it through the previous loop, all places are taken, so it's a tie
		return 1;
		
	}
	
	public int getComputerMove(){
		int move;
		
		// First see if there's a move O can make to win
				for (int i = 0; i < SIZE; i++) {
					if (mBoard[i] != HUMAN_PLAYER && mBoard[i] != COMPUTER_PLAYER) {
						char curr = mBoard[i];
						mBoard[i] = COMPUTER_PLAYER;
						if (checkForWinner() == 3) {
							mBoard[i] = NOTHING;   // Restore space
							return i;
						}
						else
							mBoard[i] = curr;
					}
				}

				// See if there's a move O can make to block X from winning
				for (int i = 0; i < SIZE; i++) {
					if (mBoard[i] != HUMAN_PLAYER && mBoard[i] != COMPUTER_PLAYER) {
						char curr = mBoard[i];   // Save the current number
						mBoard[i] = HUMAN_PLAYER;
						if (checkForWinner() == 2) {
							mBoard[i] = NOTHING;   // Restore space
							return i;
						}
						else
							mBoard[i] = curr;
					}
				}

				// Generate random move
				do {
					move = mrand.nextInt(SIZE);
				} while (mBoard[move] != NOTHING);
					
				return move;
	}
	

}
