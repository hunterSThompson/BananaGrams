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

    GameData fakeGameData = new GameData();

    public Graphics() {
        gridColor.setColor(Color.BLACK);
        gridColor.setStrokeWidth(5f);

        backgroundColor.setColor(Color.WHITE);

        textColor.setTextSize(50);
        textColor.setColor(Color.BLUE);

        fakeGameData = new GameData();
        fakeGameData.gameTiles = getTiles();
        fakeGameData.xStart = 47;
        fakeGameData.xEnd= 52;
        fakeGameData.yStart = 47;
        fakeGameData.yEnd = 52;
    }

    // TODO: get rid of this crap
    private GameTile[][] getTiles()
    {
        // TODO: cleanthis up
        GameTile gt1 = new GameTile("", TileStatus.Empty);
        GameTile gt2 = new GameTile("A", TileStatus.Neutral);
        GameTile gt3 = new GameTile("B", TileStatus.Empty);
        GameTile gt4 = new GameTile("C", TileStatus.Empty);
        GameTile gt5 = new GameTile("D", TileStatus.Empty);
        GameTile gt6 = new GameTile("E", TileStatus.Empty);

        GameTile[][] gts = new GameTile[100][100];

        for (int i = 0; i < 100; i++)
            for (int j = 0; j < 100; j++)
            {
                gts[i][j] = new GameTile("", TileStatus.Empty);
            }

        int i= 0;
        int x = i++;
        gts [50][50] = gt2;
        return gts;
    }

    public void Draw(Canvas canvas, int height, int width, GameData gameData) {
        GameTile[][] tilesToDraw = new GameTile[6][6];
        try
        {
            tilesToDraw = GetVisibleLetters(fakeGameData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        DrawBackground(canvas, height, width);
        DrawLetters(canvas, height, width, tilesToDraw);

        //DrawLetters(canvas, height, width);
        //DrawRects(canvas, height, width);
    }

    private void DrawGameTiles(GameTile[][] gameTiles)
    {
    }

    private void DrawLetters(Canvas canvas, int height, int width, GameTile[][] gameTiles)
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

                String letter = gameTiles[i][j]._letter;

                canvas.drawText(letter, x, y, textColor);
            }
        }
    }


    private GameTile[][] GetVisibleLetters(GameData gameData) throws Exception {
        GameTile[][] tilesToDraw = new GameTile[6][6];

        int x = 0;
        int x1 = 0;
        x1 = x + x1 + 2;

        int numX = 0;
        int numY = 0;

        int difX = Math.abs(gameData.xStart - gameData.xEnd);
        int difY = Math.abs(gameData.yStart - gameData.yEnd);
        if (difX != 6 || difY != 6)
        {
            //throw new Exception("Illegal num of tiles!!!");
         }


        for (int i = gameData.xStart; i < gameData.xEnd; i++)
        {
            for (int j = gameData.yStart; j < gameData.yEnd; j++)
            {
                if (i == 50 && j == 50)
                {
                    int fake = 11111;
                    fake ++;
                }
                GameTile g = gameData.gameTiles[i][j];
                String let = g._letter;
                tilesToDraw[numX][numY] = gameData.gameTiles[i][j];
                numY++;
            }
            numX++;
            numY = 0;
        }

        int x2 = 5 + x1;

        return tilesToDraw;
    }

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
