package com.apps.hgb.bananagrams;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hunt on 11/6/2014.
 */
public class GameData {

    public GameTile[][] gameTiles = new GameTile[100][100];

    public int yStart;
    public int xStart;
    public int xEnd;
    public int yEnd;

    public GameTile SelectedTile = null;
    public GameActivity gameActivity;

    public List<GameTile> CachedTiles = new ArrayList<GameTile>();
}
