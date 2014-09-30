package com.apps.hgb.bananagrams;

/**
 * Created by Hunt on 9/29/2014.
 */

public class GameTile {

    public GameTile(String letter, TileStatus _tileStatus) {
        _letter = letter;
        tileStatus = _tileStatus;
    }

    public String _letter;
    public TileStatus tileStatus;
}

