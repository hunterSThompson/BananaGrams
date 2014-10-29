package com.apps.hgb.bananagrams;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * Created by Hunt on 9/28/2014.
 */
public class Graphics {

    //
    //  Paints
    //
    Paint gridColor = new Paint();
    Paint backgroundColor = new Paint();
    Paint textColor = new Paint();
    Paint selectedTileGreen = new Paint();
    Paint selectedTileRed = new Paint();
    Paint blue = new Paint();
    Paint yellow = new Paint();

    //
    //  Fake game data member for dev
    //
    Game fakeGame = new Game();

    public Graphics() {
        InitializeGraphics();
    }

    private void InitializeGraphics()
    {
        // Set paint colors
        gridColor.setColor(Color.WHITE);
        gridColor.setStrokeWidth(5f);

        backgroundColor.setColor(Color.BLUE);
        backgroundColor.setAlpha(10);

        textColor.setTextSize(50);
        textColor.setColor(Color.BLACK);

        selectedTileGreen.setColor(Color.GREEN);
        selectedTileGreen.setTextSize(50);

        selectedTileRed.setColor(Color.RED);
        selectedTileRed.setTextSize(50);

        blue.setColor(Color.BLUE);
        yellow.setColor(Color.YELLOW);

        // Create fake game data
        fakeGame = new Game();

        // Initialize view frame
        fakeGame.xStart = 47;
        fakeGame.xEnd= 52;
        fakeGame.yStart = 47;
        fakeGame.yEnd = 52;
    }

    public void Draw(Canvas canvas, int height, int width, Game game) {
        DrawLetters3(canvas, height, width, game);
    }

    private void DrawLetters3(Canvas canvas, int height, int width, Game game)
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

    private void DrawLetters(Canvas canvas, int height, int width, Game game)
    {
        GameTile[][] gameTiles = new GameTile[6][6];
        gameTiles = game.GetVisibleLetters();

        float d1 = height / 6f * (1/2f);
        float d2 = width / 6f * (1/2f);

        float x = 20f;
        float y = 0f;

        // Draw each letter
        for (int i = 0; i < 6; i++)
        {
            y = i * height / 6 + d1;  // Calculate xCords
            y += 20f;  // Offset to account for font size

            for (int j = 0; j < 6; j++)
            {
                x = j * width / 6 + d2; // Calculate yCords
                x -= 17f; // Calculate offset TODO: Change this to width of text / 2

                Draw(canvas, x, y, gameTiles[i][j]);
            }
        }
    }

    private void Draw(Canvas c, RectF rect, GameTile gameTile)
    {
        if (gameTile == null)
            return;

        Paint colorToPaint = null;

        switch (gameTile.tileStatus) {
            case Empty:
                colorToPaint = blue;
                break;
            case Neutral:
                colorToPaint = yellow;
                break;
            case SelectedGreen:
                colorToPaint = selectedTileGreen;
                break;
            case SelectedRed:
                colorToPaint = selectedTileRed;
                break;
        };

        String letter = gameTile._letter;

        float textHeight = textColor.measureText(letter, 0, letter.length());
        float x = rect.left; // + rect.width(); // 2f; // 4;
        float y = rect.top + textHeight; // + rect.height() / 2f; // / 2;

        c.drawRect(rect, colorToPaint);
        c.drawText(letter, x, y, textColor);
    }

    private static Paint getPaint(GameTile gt)
    {
        Paint colorToPaint = null;

        /*
        switch (ts) {
            case Empty:
                return;
            case Neutral:
                colorToPaint = textColor;
                break;
            case SelectedGreen:
                colorToPaint = selectedTileGreen;
                break;
            case SelectedRed:
                colorToPaint = selectedTileRed;
                break;
        };
        */
        return null;

    }

    private void Draw(Canvas c, float x, float y, GameTile gameTile)
    {
        if (gameTile == null)
            return;

        String letter = gameTile._letter;
        TileStatus ts = gameTile.tileStatus;

        Paint colorToPaint = null;

        switch (ts) {
            case Empty:
                return;
            case Neutral:
                colorToPaint = textColor;
                break;
            case SelectedGreen:
                colorToPaint = selectedTileGreen;
                break;
            case SelectedRed:
                colorToPaint = selectedTileRed;
                break;
        };

        c.drawText(letter, x, y, colorToPaint);
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

    //
    // No longer using this
    //
    private void DrawLetters(Canvas canvas, int height, int width)
    {
        float d1 = height / 6f * (1/2f);
        float d2 = width / 6f * (1/2f);
        
        float x = 20f;
        float y = 0f;

        // Draw each letter
        for (int i = 0; i < 6; i++)
        {
            y = i * height / 6 + d1;  // Calculate xCords
            y += 20f;  // Offset to account for font size
            for (int j = 0; j < 6; j++)
            {
                x = j * width / 6 + d2; // Calculate yCords
                x -= 17f; // Calculate offset
                canvas.drawText("A", x, y, textColor);
            }
        }
    }

    // NOt using this for now
    private void DrawRects(Canvas canvas, int height, int width)
    {
        float qBX = width * 1 / 6f;
        float qBY = height * 1 / 6f;

        float left = 0 + qBX * 1 / 4f;
        float right = 0 + qBX * 3 / 4f;
        float top = 0 + qBY;
        float bottom = 0 + qBY * 3 / 4f;

        canvas.drawRect(left, top, right, bottom, textColor);
    }


}
