package com.apps.hgb.bananagrams;

/**
 * Created by Hunt on 9/29/2014.
 */

public class GameTile {

    public String letter;
    public TileStatus tileStatus;

    public boolean Selected;
    public boolean HasLetter;

    /*
    public GameTile(String letter, TileStatus _tileStatus) {
        this.letter = letter;
        this.tileStatus = _tileStatus;
    }
    */

    public GameTile(String letter, boolean selected, boolean hasLetter)
    {
        this.letter = letter;
        this.Selected = selected;
        this.HasLetter = hasLetter;
    }

    public void UnHighlight()
    {
        if (tileStatus == TileStatus.SelectedEmpty)
        {
            tileStatus = TileStatus.Empty;
        }
        else if (tileStatus == TileStatus.SelectedFilled)
        {
            tileStatus = TileStatus.Filled;
        }
    }

    //
    //  Returns true if Tile needs to be added to tray
    //
    public boolean TouchSelectedTile()
    {
        SetToEmpty();
        if (tileStatus == TileStatus.SelectedEmpty)
            return true;
        else if (tileStatus == TileStatus.Empty.SelectedFilled) {
            Delete();
        }
        return false;
    }

    public void SetToEmpty()
    {
        this.tileStatus = TileStatus.Empty;
    }

    public void Delete()
    {
        this.letter = "";
    }

    public void SetLetter(String letter)
    {
        this.letter = letter;
    }
}

