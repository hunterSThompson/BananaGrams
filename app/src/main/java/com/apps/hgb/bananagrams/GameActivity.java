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
        boolean continuingGame = getIntent().getBooleanExtra("continueGame", false);
        if (continuingGame)
            game = new Game(this, true);
        else
            game = new Game(this, false);

        // Initialize game board
        gameBoard = new GameBoard(this, game);
        ((RelativeLayout) findViewById(R.id.gameCont)).addView(gameBoard);

        //
        //
        final ButtonTray adapter = new ButtonTray(this, game);
        buttonTray = (GridView) findViewById(R.id.ButtonContainer);
        buttonTray.setAdapter(adapter);
        buttonTray.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                adapter.RemoveItem(position);
                //Toast.makeText(GameActivity.this, "You Clicked at " + web1.get(+position), Toast.LENGTH_SHORT).show();

            }
        });
        tray = adapter;

        //buttonTray.setOnClickListener(new AdapterView.set);
        // Init Grid View here
        //
        //

        gameBoard.requestFocus();

        // todo recode.
        getButtons2();
    }

    @Override
    public void onResume(){
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

    // TODO implement
    public boolean IsTrayEmpty()
    {
        return false;
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
