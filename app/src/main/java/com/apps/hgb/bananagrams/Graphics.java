package com.apps.hgb.bananagrams;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

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

        // Create fake game data
        fakeGame = new Game();
        fakeGame.gameTiles = getTiles();

        // Initialize view frame
        fakeGame.xStart = 47;
        fakeGame.xEnd= 52;
        fakeGame.yStart = 47;
        fakeGame.yEnd = 52;
    }

    // TODO: get rid of this after done testing
    public GameTile[][] getTiles()
    {
        // TODO: cleanthis up
        GameTile gt1 = new GameTile("", TileStatus.Empty);
        GameTile gt2 = new GameTile("A", TileStatus.Neutral);
        GameTile gt3 = new GameTile("B", TileStatus.Empty);
        GameTile gt4 = new GameTile("C", TileStatus.Empty);
        GameTile gt5 = new GameTile("D", TileStatus.Empty);
        GameTile gt6 = new GameTile("E", TileStatus.Empty);

        GameTile[][] gts = new GameTile[100][100];

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                gts[i][j] = new GameTile("", TileStatus.Empty);
            }
        }

        int i= 0;
        int x = i++;
        gts [50][50] = gt2;
        gts [49][49] = gt2;
        gts [50][48] = gt2;
        gts [48][50] = gt2;
        gts [51][50] = gt2;
        return gts;
    }

    public void Draw(Canvas canvas, int height, int width, Game game) {
        DrawBackground(canvas, height, width);
        DrawLetters(canvas, height, width, game);
    }

    private void DrawLetters3(Canvas canvas, int height, int width, Game game)
    {
        GameTile[][] gameTiles = game.GetVisibleLetters();

        for (int i = 0; i < 6; i ++)
        {
            float top = height * (i/6f);
            float bottom = height * ((i+1)/6f);
            for (int j = 0; j < 6; j++)
            {
                float left = width * (j/6f);
                float right = width * ((j+1)/6f);
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

    //
    //  TODO:  Implement code here to just draw the stupid rectangles with drawRect. What was I thinking with grid lines
    //
    private void DrawLetters2(Canvas canvas, int height, int width, Game game)
    {
        GameTile[][] gameTiles = new GameTile[6][6];
        gameTiles = game.GetVisibleLetters();

        float d1 = height / 6f * (1/2f);
        float d2 = width / 6f * (1/2f);

        float x;
        float y;

        // Draw each letter
        for (int i = 0; i < 6; i++)
        {
            y = i * height / 6 + d1;  // Calculate xCords
            float y2 = y;
            y += 20f;  // Offset to account for font size

            for (int j = 0; j < 6; j++)
            {
                x = j * width / 6 + d2; // Calculate yCords
                float x2 = x;

                //canvas.drawRect(canvas, x+20f, );

                x -= 17f; // Calculate offset TODO: Change this to width of text / 2

                Draw(canvas, x, y, gameTiles[i][j]);
                //drawRect
            }
        }
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
