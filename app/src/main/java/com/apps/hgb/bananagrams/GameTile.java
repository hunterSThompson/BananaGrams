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

    public void SetToEmpty()
    {
        tileStatus = TileStatus.Empty;
    }

    public void SetToNeutral(String letter)
    {
        tileStatus = TileStatus.Neutral;
        _letter = letter;
    }

    //
    //  Used when a tile is selected and all green/red tiles need to be neutral again.
    //  TODO: Considering there are 100*100 = 10000 tiles
    //
    public void SetToNeutral()
    {
        tileStatus = TileStatus.Neutral;
    }

    public void SetToGreen()
    {
        tileStatus = TileStatus.SelectedGreen;
    }

    public void SetToRed()
    {
        tileStatus = TileStatus.SelectedRed;
    }

    public String _letter;
    public TileStatus tileStatus;
}

