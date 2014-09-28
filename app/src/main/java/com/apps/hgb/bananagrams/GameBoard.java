package com.apps.hgb.bananagrams;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Hunt on 9/27/2014.
 */
public class GameBoard extends View {

    GameActivity gameActivity;
    Paint backgroundColor = new Paint(Color.CYAN);

    public GameBoard(Context context) {
        super(context);

        setFocusable(true);
        setFocusableInTouchMode(true);

        this.gameActivity = (GameActivity) context;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        canvas.drawRect(0, getHeight(), getWidth(), getHeight(), backgroundColor);
    }
}
