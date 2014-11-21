package com.apps.hgb.bananagrams;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;


public class GameActivity extends Activity {

    Game game;
    GameBoard gameBoard;
    GridView buttonTray;
    ButtonTray tray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        // Continue or start New Game
        boolean continuingGame = getIntent().getBooleanExtra("continueGame", false); // todo move string to constants
        if (continuingGame)
            game = new Game(this, true);
        else
            game = new Game(this, false);

        // Initialize game board
        gameBoard = new GameBoard(this, game);
        ((RelativeLayout) findViewById(R.id.gameCont)).addView(gameBoard);

        // Initialize tile tray
        tray = new ButtonTray(this, game);
        buttonTray = (GridView) findViewById(R.id.ButtonContainer);
        buttonTray.setAdapter(tray);

        gameBoard.requestFocus();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if (game == null)
            game = new Game(this, true);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        if (game != null)
            game.SaveState();
    }

    public boolean IsTrayEmpty()
    {
        return tray.getCount() < 1;
    }

    public void PopTile(String letter)
    {
        tray.AddTile(letter);
    }

    public void InvalidateGameBoard()
    {
        gameBoard.Invalidate();
    }

    public void rightClick(View v)
    {
        game.MoveBoard(Direction.Right);
        gameBoard.invalidate();
    }

    public void leftClick(View v)
    {
        game.MoveBoard(Direction.Left);
        gameBoard.invalidate();
    }

    public void upClick(View v)
    {
        game.MoveBoard(Direction.Up);
        gameBoard.invalidate();
    }

    public void downClick(View v)
    {
        game.MoveBoard(Direction.Down);
        gameBoard.invalidate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game, menu);
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
}
