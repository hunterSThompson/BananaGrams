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
                return;
            case Neutral:
                tileStatus = tileStatus.SelectedGreen;
                break;
            case SelectedGreen:
                tileStatus = tileStatus.SelectedRed;
                break;
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

