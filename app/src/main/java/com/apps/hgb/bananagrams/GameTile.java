package com.apps.hgb.bananagrams;


import java.io.Serializable;

/**
 * Created by Hunt on 9/29/2014.
 */

public class GameTile implements Serializable {

    public String letter;

    public boolean Selected;
    public boolean HasLetter;

    public int X;
    public int Y;

    public GameTile(int x, int y)
    {
        this.X = x;
        this.Y = y;
        letter = "";
        Selected = false;
        HasLetter = false;
    }

    public void ClearTile()
    {
        this.letter = "";
        this.HasLetter = false;
        this.Selected = false;
    }

    public void UnSelect()
    {
        this.Selected = false;
    }

    public void Select()
    {
        this.Selected = true;
    }

    public void SetLetter(String letter)
    {
        this.HasLetter = true;
        this.Selected = false;
        this.letter = letter;
    }

    // TODO Add methods for Delete/Highlight/Unhighlight

    public GameTile(GameTile gameTile)
    {
        this.letter = gameTile.letter;
        this.Selected = gameTile.Selected;
        this.HasLetter = gameTile.HasLetter;
        this.X = gameTile.X;
        this.Y = gameTile.Y;
    }

    public void LoadState(GameTile gameTile)
    {
        this.letter = gameTile.letter;
        this.Selected = gameTile.Selected;
        this.HasLetter = gameTile.HasLetter;
        this.X = gameTile.X;
        this.Y = gameTile.Y;
    }

}

