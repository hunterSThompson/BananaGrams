package com.apps.hgb.bananagrams;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Hunt on 9/28/2014.
 */
public class Graphics {

    //
    //
    //
    Paint gridColor = new Paint();
    Paint backgroundColor = new Paint();

    public Graphics()
    {
        gridColor.setColor(Color.BLACK);
        gridColor.setStrokeWidth(5f);
        backgroundColor.setColor(Color.WHITE);
    }

    public void Draw(Canvas canvas, int height, int width)
    {
        int numSquares = 6;
        float startX, startY, endX, endY = 0f;

        // Draw background
        canvas.drawRect(0, 0, width, height, backgroundColor);

        for (int i = 1; i < numSquares + 1; i++)
        {
            startX = width * i / numSquares;
            startY = 0;
            endX = width * i / numSquares;
            endY = height;
            canvas.drawLine(startX, startY, endX, endY, gridColor);

            startX = 0;
            startY = height * i / numSquares;
            endX = width;
            endY = height * i / numSquares;
            canvas.drawLine(startX, startY, endX, endY, gridColor);
        }

    }
}
