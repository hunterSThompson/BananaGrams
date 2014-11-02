package com.apps.hgb.bananagrams;

/**
 * Created by Hunt on 10/2/2014.
 */
public final class Utilities {

    static GameTile GetTouchedTile2(float x, float y, Game game, float height, float width)
    {
        int xf = game.xStart + (int) (x / width / 6);
        int yf = game.yStart + (int) (y / height / 6);
        GameTile gt = game.gameTiles[xf][yf];
        return game.gameTiles[xf][yf];
    }

    static GameTile GetTouchedTile(float x, float y, Game game, float height, float width)
    {
        float cellWidth = width / 6;
        float cellHeight = height / 6;

        float tileX = x / cellWidth;
        float tileY = y / cellHeight;

        int xTileNum = (int) tileX;
        int yTileNum = (int) tileY;

        int xf = xTileNum + game.xStart;
        int yf = yTileNum + game.yStart;

        // TODO:  Throw WTF error here
        if ((xf | yf) > 100)
        {
        }
        if ((xf | yf) < 0)
        {
        }

        GameTile gt = game.gameTiles[xf][yf];
        return game.gameTiles[xf][yf];
    }
}
