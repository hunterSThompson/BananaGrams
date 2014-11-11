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

    // TODO Add methods for Delete/Highlight/Unhighlight

    public GameTile(String letter, boolean selected, boolean hasLetter)
    {
        this.letter = letter;
        this.Selected = selected;
        this.HasLetter = hasLetter;
    }

    public void SetLetter(String letter)
    {
        this.letter = letter;
    }
}

