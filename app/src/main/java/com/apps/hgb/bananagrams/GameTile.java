package com.apps.hgb.bananagrams;

/**
 * Created by Hunt on 9/29/2014.
 */

public class GameTile {

    public String letter;

    public boolean Selected;
    public boolean HasLetter;

    public GameTile()
    {
        letter = "";
        Selected = false;
        HasLetter = false;
    }

    public GameTile(String letter, boolean selected, boolean hasLetter)
    {
        this.letter = letter;
        this.Selected = selected;
        this.HasLetter = hasLetter;
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

