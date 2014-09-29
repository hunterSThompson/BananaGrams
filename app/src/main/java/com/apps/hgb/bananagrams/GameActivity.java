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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //
        //  Check intent resume game
        //

        GameBoard gb = new GameBoard(this);
        gameContainer = (RelativeLayout) findViewById(R.id.gameCont);
        gameContainer.addView(gb);

        // TODO: Finish fixing border issue below. Really really annoying.
        View bottomBorder = new View(this);
        bottomBorder.setBackgroundColor(Color.BLUE);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 1);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

        gameContainer.addView(bottomBorder, params);

        gb.requestFocus();
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
