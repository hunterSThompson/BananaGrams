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
    Paint textColor = new Paint();

    public Graphics() {
        gridColor.setColor(Color.BLACK);
        gridColor.setStrokeWidth(5f);

        backgroundColor.setColor(Color.WHITE);

        textColor.setTextSize(50);
        textColor.setColor(Color.BLUE);
    }

    public void Draw(Canvas canvas, int height, int width, GameData gameData) {
        DrawBackground(canvas, height, width);
        DrawLetters(canvas, height, width);
    }

    private void DrawLetters(GameData gameData) {
    }

    private void DrawLetters(Canvas canvas, int height, int width)
    {
        float d = height / 6f * (1/2f);
        float y = 20f;

        float x = 0f;
        for (int i = 0; i < 6; i++)
        {
            x = i * height / 6 + d; // Calculate xCords
            x += 20f; // Offset to accont for font size
            canvas.drawText("A", y, x, textColor);
        }
    }

    private void DrawBackground(Canvas canvas, int height, int width)
    {
        int numSquares = 6;
        float startX, startY, endX, endY = 0f;

        // Draw background
        canvas.drawRect(0, 0, width, height, backgroundColor);

        // Draw Grid Lines
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
