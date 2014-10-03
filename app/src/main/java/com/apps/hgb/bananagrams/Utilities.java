package com.apps.hgb.bananagrams;

/**
 * Created by Hunt on 10/2/2014.
 */
public final class Utilities {

    static boolean getBool()
    {
        return true;
    }

    static GameTile GetTouchedTile(float x, float y, Game game, float height, float width)
    //static GameTile GetTouchedTile(float x, float y, Game game)
    {
        float cellWidth = width / 6;
        float cellHeight = height / 6;

        float tileX = x / cellWidth;
        float tileY = y / cellHeight;

        //float tileX = x / 6;
        //float tileY = y / 6;

        int xTileNum = (int) tileX;
        int yTileNum = (int) tileY;

        // Convert to index

        int xf = xTileNum + game.xStart;
        int yf = yTileNum + game.yStart;

        GameTile gt = game.gameTiles[xf][yf];


        if ((xf | yf) > 100)
        {
        }
        if ((xf | yf) < 0)
        {
        }

        return game.gameTiles[xf][yf];
    }
}
