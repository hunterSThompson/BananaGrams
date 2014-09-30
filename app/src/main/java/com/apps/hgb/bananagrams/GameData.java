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
