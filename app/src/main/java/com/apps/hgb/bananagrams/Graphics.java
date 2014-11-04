package com.apps.hgb.bananagrams;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by Hunt on 9/28/2014.
 */
public class Graphics {

    //
    //  Paints
    //
    Paint neutralColor = new Paint();
    Paint textColor = new Paint();
    Paint selectedTileGreen = new Paint();
    Paint selectedTileRed = new Paint();

    public Graphics() {
        InitializeGraphics();
    }

    private void InitializeGraphics()
    {
        // Set paint colors
        neutralColor.setColor(Color.BLUE);
        neutralColor.setAlpha(10);

        textColor.setTextSize(50);
        textColor.setColor(Color.BLACK);

        selectedTileGreen.setColor(Color.GREEN);
        selectedTileGreen.setAlpha(128);

        selectedTileRed.setColor(Color.RED);
        selectedTileRed.setAlpha(128);
    }

    public void Draw(Canvas canvas, int height, int width, Game game) {
        DrawLetters(canvas, height, width, game);
    }

    private void DrawLetters(Canvas canvas, int height, int width, Game game)
    {
        GameTile[][] gameTiles = game.GetVisibleLetters();

        float top, bottom, left, right;
        RectF rect;

        for (int i = 0; i < 6; i ++)
        {
            top = height * (i/6f) + 4f;
            bottom = height * ((i+1)/6f) - 4f;
            for (int j = 0; j < 6; j++)
            {
                left = width * (j/6f) + 4f;
                right = width * ((j+1)/6f) - 4f;
                rect = new RectF(left, top, right, bottom);
                Draw(canvas, rect, gameTiles[j][i]);
            }
        }
    }

    private void Draw(Canvas c, RectF rect, GameTile gameTile)
    {
        if (gameTile == null)
            return;

        Paint colorToPaint = null;

        if (gameTile.Selected)
            colorToPaint = selectedTileGreen;
        else
            colorToPaint = neutralColor;

        String letter = gameTile.letter;

        float textHeight = textColor.measureText(letter, 0, letter.length());
        float x = rect.left; // + rect.width(); // 2f; // 4;
        float y = rect.top + textHeight; // / 2;

        c.drawRect(rect, colorToPaint);
        c.drawText(letter, x, y, textColor);
    }

}
