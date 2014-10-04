package com.apps.hgb.bananagrams;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Hunt on 9/27/2014.
 */
public class GameBoard extends View {

    GameActivity gameActivity;
    Graphics g;
    Game game;

    public GameBoard(Context context, Game _game) {
        super(context);

        g = new Graphics();
        gameActivity = (GameActivity) context;

        game = _game;

        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    public void Invalidate()
    {
        Invalidate();
    }

    //  TODO: Implement continue game constructor
    public GameBoard(Context context, String savedGameData)
    {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        g.Draw(canvas, getHeight(), getWidth(), game);

        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        game.BoardClick(x, y, getHeight(), getWidth());
        invalidate();

        return super.onTouchEvent(event);
    }

}
