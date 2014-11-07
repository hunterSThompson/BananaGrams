package com.apps.hgb.bananagrams;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;


public class GameActivity extends Activity {

    RelativeLayout gameContainer;
    Game game;
    GameBoard gameBoard;
    LinearLayout buttonTray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        buttonTray = (LinearLayout) findViewById(R.id.ButtonContainer);

        //  TODO: Check intent resume game
        boolean continuingGame = getIntent().getBooleanExtra("continueGame", false);
        // if Intent == newGame
        if (!continuingGame)
            game = new Game(this);
        else
        {
            game = new Game(this, "");
        }

        gameBoard = new GameBoard(this, game);
        gameContainer = (RelativeLayout) findViewById(R.id.gameCont);
        gameContainer.addView(gameBoard);

        gameBoard.requestFocus();

        String[] letts = Utilities.ShuffleGameLetters();

        getButtons2();
    }

    private void InitTrayButtons()
    {
        String[] letts = Utilities.ShuffleGameLetters();
        for (int i = 0; i < letts.length; i++)

            for (int j = 0; j < 9; j++) {
                LinearLayout row = new LinearLayout(this);
                row.setOrientation(LinearLayout.HORIZONTAL);
                //row.setLayoutParams(new LinearLayout.LayoutParams(
                        //LinearLayout.LayoutParams.FILL_PARENT,
                //));
            }
    }

    private ArrayList<ArrayList<Button>> getButtons()
    {
        if (buttonTray == null)
            return null; // Log error also

        ArrayList<ArrayList<Button>> rows = new ArrayList<ArrayList<Button>>();
        for (int i = 0; i < buttonTray.getChildCount(); i++)
        {
            LinearLayout row = (LinearLayout) buttonTray.getChildAt(i);
            rows.add(getButtonsFromRow(row));
        }
        return rows;
    }

    private ArrayList<Button> getButtonsFromRow(LinearLayout row)
    {
        ArrayList<Button> buttons = new ArrayList<Button>();
        for (int i=0; i < buttonTray.getChildCount(); i++){
            buttons.add((Button)row.getChildAt(i));
        }
        return buttons;
    }

    private void getButtons2()
    {
        if (buttonTray == null)
            return; // Log error also

        for (int i = 0; i < buttonTray.getChildCount(); i++)
        {
            LinearLayout row = (LinearLayout) buttonTray.getChildAt(i);
            getButtonsFromRow2(row);
        }
    }

    private void getButtonsFromRow2(LinearLayout row)
    {
        for (int i=0; i < row.getChildCount(); i++){
            Button b = (Button)row.getChildAt(i);
            setButtonClickListener(b);
        }
    }

    private void setButtonClickListener(Button b)
    {
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Button b = (Button) v;
                String text = b.getText().toString();

                if (!game.AddTileToBoard(text))
                    return;
                ((ViewGroup) v.getParent()).removeView(v);
                gameBoard.Invalidate();
            }
        });
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
