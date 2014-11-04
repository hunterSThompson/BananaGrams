package com.apps.hgb.bananagrams;

import android.graphics.Color;
import android.graphics.Point;
import android.view.View;

/**
 * Created by Hunt on 9/28/2014.
 */
public class Game {

    /********************************************************************
     * Members
     ********************************************************************/
    public GameTile[][] gameTiles = new GameTile[100][100];

    public int yStart;
    public int xStart;
    public int xEnd;
    public int yEnd;

    private GameTile SelectedTile = null;
    private GameActivity gameActivity;

    /********************************************************************
     * Constructor
    ********************************************************************/
    public GameTile[][] GetVisibleLetters() {

        GameTile[][] tilesToDraw = new GameTile[6][6];

        int numX = 0;
        int numY = 0;

        int difX = Math.abs(this.xStart - this.xEnd);
        int difY = Math.abs(this.yStart - this.yEnd);

        if (difX != 5 || difY != 5)
        {
            // TODO: The app should crash if our view frame isn't the right num of things...
        }

        for (int i = this.xStart; i < this.xEnd; i++)
        {
            for (int j = this.yStart; j < this.yEnd; j++)
            {
                tilesToDraw[numX][numY] = this.gameTiles[i][j];
                numY++;
            }
            numX++;
            numY = 0;
        }

        return tilesToDraw;
    }

    // TODO: Implement Game constructor.  Should randomly generate tiles
    public Game(GameActivity gameActivity) {
        this.gameActivity = gameActivity;
        InitializeGraphics();
    }

    // TODO:
    public Game(GameActivity gameActivity, String data)
    {
        this.gameActivity = gameActivity;
        //Deserialize()
    }


    /********************************************************************
     * Public Methods
     ********************************************************************/
    private void InitializeGraphics()
    {
        InitializeTiles();

        // Initialize view frame
        this.xStart = 47;
        this.xEnd= 53;
        this.yStart = 47;
        this.yEnd = 53;
    }

    private void InitializeTiles()
    {
        GameTile[][] gts = new GameTile[100][100];

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                gts[i][j] = new GameTile();
            }
        }

        gameTiles = gts;
    }

    // TODO: Change these one method with switch later
    public void MoveLeft()
    {
        xStart += 1;
        xEnd += 1;
    }

    public void MoveRight()
    {
        xStart -= 1;
        xEnd -= 1;
    }

    public void MoveDown()
    {
        yStart -= 1;
        yEnd -= 1;
    }

    public void MoveUp()
    {
        yStart += 1;
        yEnd += 1;
    }

    public void BoardClick(float x, float y, float height, float width)
    {
        GameTile touchedTile = Utilities.GetTouchedTile(x, y, this, height, width);

        if (SelectedTile == null) {
            touchedTile.Selected = true;
            SelectedTile = touchedTile;
        }
        else
        {
            // TODO: Fixed logic here!
            // If you:
            // 1) Add a tile
            // 2) Add another tile
            // 3) Highlight one
            // 4) Click the other
            // The other will be deleted. Fix logic
            // Main thing: there is no check if touched tile is selected tile

            //if (touchedTile.HasLetter)
            if (SelectedTile == touchedTile)
            {
                PopTile(touchedTile);
                touchedTile.letter = "";
                touchedTile.Selected = false;
                touchedTile.HasLetter = false;
            }
            else
            {
                SelectedTile.Selected = false;
            }
            SelectedTile = null;
        }
    }

    //
    //  Move tile from Board to Tray
    //
    private void PopTile(GameTile gt)
    {
        //gameActivity.PopTile();
    }

    //
    //  Adds a tile to the game board from the tray.
    //  Returns true if successful (if no tile is highlighted then it can't be added)
    //
    public boolean AddTileToBoard(String letter)
    {
        if (SelectedTile == null)
            return false;

        SelectedTile.letter = letter;
        SelectedTile.HasLetter = true;
        SelectedTile.Selected = false;
        SelectedTile = null;
        return true;
    }


    // TODO: Implement
    private Game Deserialize(String data)
    {
        return null;
    }

    // TODO: Implement
    private String Serialize()
    {
        return "";
    }

    // TODO: Implement
    public boolean GameOver()
    {
        return false;
    }
}
