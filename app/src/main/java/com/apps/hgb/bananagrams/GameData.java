package com.apps.hgb.bananagrams;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Hunt on 9/28/2014.
 */
public class GameData {

    public GameTile[][] gameTiles = new GameTile[100][100];

    public String[] selectableTiles = new String[50];

    public Range xRange;
    public Range yRange;

    public int yStart;
    public int xStart;
    public int xEnd;
    public int yEnd;

    public GameTile[][] GetVisibleLetters() throws Exception {
        GameTile[][] tilesToDraw = new GameTile[6][6];

        int x = 0;
        int x1 = 0;
        x1 = x + x1 + 2;

        int numX = 0;
        int numY = 0;

        int difX = Math.abs(this.xStart - this.xEnd);
        int difY = Math.abs(this.yStart - this.yEnd);
        if (difX != 6 || difY != 6)
        {
            throw new Exception("Illegal num of tiles!!!");
        }

        GameTile g;
        for (int i = this.xStart; i < this.xEnd; i++)
        {
            for (int j = this.yStart; j < this.yEnd; j++)
            {
                g = this.gameTiles[i][j];
                String let = g._letter;
                tilesToDraw[numX][numY] = this.gameTiles[i][j];
                numY++;
            }
            numX++;
            numY = 0;
        }

        int x2 = 5 + x1;

        return tilesToDraw;
    }


    // TODO: Implement GameData constructor.  Should randomly generate tiles
    public GameData()
    {
    }

    // TODO: Implement resume game constructor.  Should call deserialize function.
    public GameData(String data)
    {
    }

    private GameData(GameTile[][] data)
    {
    }

    // TODO: Implement deserialze func
    private GameData Deserialize(String data)
    {
        return null;
    }

    // TODO: Implement
    public String Serialize()
    {
        return "";
    }
}
