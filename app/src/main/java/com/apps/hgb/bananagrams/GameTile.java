package com.apps.hgb.bananagrams;

/**
 * Created by Hunt on 9/29/2014.
 */

public class GameTile {

    public GameTile(String letter, TileStatus _tileStatus) {
        _letter = letter;
        tileStatus = _tileStatus;
    }

    public void Touch()
    {
        switch (tileStatus) {
            // Should never happen
            case Empty:
                tileStatus = TileStatus.SelectedGreen;
                return;
            case Neutral:
                tileStatus = TileStatus.SelectedGreen;
                return;
            case SelectedGreen:
                tileStatus = TileStatus.SelectedRed;
                return;
            // Should never happen
            case SelectedRed:
                return;
        };
    }

    public void setToEmpty()
    {
        tileStatus = TileStatus.Empty;
    }

    public String _letter;
    public TileStatus tileStatus;
}

