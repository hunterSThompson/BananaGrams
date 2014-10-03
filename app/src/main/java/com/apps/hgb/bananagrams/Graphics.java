package com.apps.hgb.bananagrams;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

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


                GameTile gt = gameTiles[i][j];
                String letter = "";

                if (gt != null)
                    letter = gameTiles[i][j]._letter;

                canvas.drawText(letter, x, y, textColor);
            }
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
