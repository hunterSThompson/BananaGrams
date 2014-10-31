package com.apps.hgb.bananagrams;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class GameActivity extends Activity {

    RelativeLayout gameContainer;
    Game game;
    GameBoard gameBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        View tileContainer = findViewById(R.id.TileContainer);

        //  TODO: Check intent resume game
        // if Intent == newGame
        game = new Game(tileContainer);
        // else
        // game = new Game(savedData);

        gameBoard = new GameBoard(this, game);
        gameContainer = (RelativeLayout) findViewById(R.id.gameCont);
        gameContainer.addView(gameBoard);

        gameBoard.requestFocus();
    }

    private void InitializeTileButtons()
    {
    }

    public void rightClick(View v)
    {
        game.MoveRight();
        gameBoard.invalidate();
    }

    public void leftClick(View v)
    {
        game.MoveLeft();
        gameBoard.invalidate();
    }

    public void upClick(View v)
    {
        game.MoveUp();
        gameBoard.invalidate();
    }

    public void downClick(View v)
    {
        game.MoveDown();
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
