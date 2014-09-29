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
        blockColor.setColor(Color.YELLOW);


        setFocusable(true);
        setFocusableInTouchMode(true);

        this.gameActivity = (GameActivity) context;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //canvas.drawRect(0, 0, getWidth(), getHeight(), backgroundColor);

        //
        canvas.drawRect(0, 0, getWidth()/8f, getHeight()/8f, blockColor);

        int numSquares = 8;
        for (int i = 1; i < numSquares + 1; i++)
        {
            // drawLine(float startX, float startY, float stopX, float stopY, Paint paint)
            //float startPoint =
            float startX = getWidth() / numSquares;
            //float startY = getHeight() / numSquares;
            float startY = 0;
            float endX = getWidth() / numSquares;
            float endY = getHeight();
            canvas.drawLine(startX, startY, endX, endY, blockColor);

            //canvas.drawLine(getWidth()/numSquares, 0, getWidth()/numSquares, getHeight(), blockColor);
        }

        blockColor.setStrokeWidth(5);

        canvas.drawLine(50, 0, 50, 500, blockColor);

        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

}
