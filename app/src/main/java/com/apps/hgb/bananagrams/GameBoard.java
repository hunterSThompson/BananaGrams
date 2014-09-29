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

    Paint backgroundColor;
    Paint blockColor;

    public GameBoard(Context context) {
        super(context);

        backgroundColor = new Paint();
        blockColor = new Paint();
        backgroundColor.setColor(Color.BLUE);
        blockColor.setColor(Color.BLACK);
        blockColor.setStrokeWidth(5f);


        setFocusable(true);
        setFocusableInTouchMode(true);

        this.gameActivity = (GameActivity) context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int numSquares = 8;
        for (int i = 1; i < numSquares + 1; i++)
        {
            float startX = getWidth() * i / numSquares;
            float startY = 0;
            float endX = getWidth() * i / numSquares;
            float endY = getHeight();
            canvas.drawLine(startX, startY, endX, endY, blockColor);

            startX = 0;
            startY = getHeight() * i / numSquares;
            endX = getWidth();
            endY = getHeight() * i / numSquares;
            canvas.drawLine(startX, startY, endX, endY, blockColor);
        }

        //canvas.drawLine(50, 0, 50, 500, blockColor);

        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

}
