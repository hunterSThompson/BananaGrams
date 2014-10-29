package com.apps.hgb.bananagrams;

/**
 * Created by Hunt on 9/29/2014.
 */

public class GameTile {

    public String letter;
    public TileStatus tileStatus;

    public GameTile(String letter, TileStatus _tileStatus) {
        this.letter = letter;
        this.tileStatus = _tileStatus;
    }

    public void Touch()
    {
        switch (tileStatus) {
            case Empty:
                tileStatus = TileStatus.SelectedGreen;
                return;
            case Neutral:
                tileStatus = TileStatus.SelectedGreen;
                return;
            case SelectedGreen:
                tileStatus = TileStatus.SelectedRed;
                return;
            case SelectedRed:
                tileStatus = TileStatus.Empty;
                //letter = "";
                return;
        };
    }

    public void SetToEmpty()
    {
        this.tileStatus = TileStatus.Empty;
        this.letter = "";
    }

    public void SetLetter(String letter)
    {
        this.tileStatus = TileStatus.Neutral;
        this.letter = letter;
    }
}

