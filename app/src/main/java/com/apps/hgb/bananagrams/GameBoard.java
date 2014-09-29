package com.apps.hgb.bananagrams;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Hunt on 9/27/2014.
 */
public class GameBoard extends View {

    GameActivity gameActivity;
    Graphics g;
    GameData gameData;

    public GameBoard(Context context) {
        super(context);

        g = new Graphics();
        this.gameActivity = (GameActivity) context;

        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        g.Draw(canvas, getHeight(), getWidth(), gameData);

        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        return super.onTouchEvent(event);
    }

}
