package com.example.tictactoe;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	private boolean mGameOver = false;
	private Button mBoardButtons[];
	private char mTurn = Game.COMPUTER_PLAYER;
	private Game mGame;
	private TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mBoardButtons = new Button[Game.SIZE];
        mBoardButtons[0] = (Button) findViewById(R.id.one);
        mBoardButtons[1] = (Button) findViewById(R.id.two); 
        mBoardButtons[2] = (Button) findViewById(R.id.three); 
        mBoardButtons[3] = (Button) findViewById(R.id.four); 
        mBoardButtons[4] = (Button) findViewById(R.id.five); 
        mBoardButtons[5] = (Button) findViewById(R.id.six); 
        mBoardButtons[6] = (Button) findViewById(R.id.seven); 
        mBoardButtons[7] = (Button) findViewById(R.id.eight); 
        mBoardButtons[8] = (Button) findViewById(R.id.nine);
        tv = (TextView) findViewById(R.id.textView1);
        mGame = new Game();
        startNewGame();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void game(View v){
		startNewGame();
	}
	
	private void startNewGame(){
		mGame.clearBoard();
		
		for(int i = 0;i<mBoardButtons.length;i++){
			mBoardButtons[i].setText("");
    		mBoardButtons[i].setEnabled(true);   
    		mBoardButtons[i].setOnClickListener(new ButtonClickListener(i));
		}
		
		if(mTurn==Game.HUMAN_PLAYER){
			mTurn = Game.COMPUTER_PLAYER;
			tv.setText(R.string.first_computer);
			int move = mGame.getComputerMove();
			setMove(Game.COMPUTER_PLAYER,move);
			tv.setText(R.string.turn_human);
		}
		else{
			mTurn = Game.HUMAN_PLAYER;
			tv.setText(R.string.first_human);
		}
		
		mGameOver = false;
	}
	
	private void setMove(char player, int location){
		mGame.setMove(player, location);
		mBoardButtons[location].setEnabled(false);
		mBoardButtons[location].setText(String.valueOf(player));
		if(player==Game.HUMAN_PLAYER){
			mBoardButtons[location].setTextColor(Color.GREEN);
		}
		else{
			mBoardButtons[location].setTextColor(Color.RED);
		}
	}

	private class ButtonClickListener implements View.OnClickListener {

		int location;
		public ButtonClickListener(int location) { 
            this.location = location; 
       }
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (!mGameOver && mBoardButtons[location].isEnabled()) {
        		setMove(Game.HUMAN_PLAYER, location);        		
            	
            	// If no winner yet, let the computer make a move
            	int winner = mGame.checkForWinner();
            	if (winner == 0) { 
            		tv.setText(R.string.turn_computer); 
            		int move = mGame.getComputerMove();
            		setMove(Game.COMPUTER_PLAYER, move);
            		winner = mGame.checkForWinner();
            	} 
            	
            	if (winner == 0)
            		tv.setText(R.string.turn_human);
            	else {        
            		// Game is over
	            	if (winner == 1) {
	            		tv.setText(R.string.result_tie); 
	            	}
	            	else if (winner == 2) {
	            		tv.setText(R.string.result_human_wins);
	            	}
	            	else if (winner == 3) {
	            		tv.setText(R.string.result_computer_wins);
	            	}
	            	
	            	mGameOver = true;
            	}
             }
		}
		
	}
}

