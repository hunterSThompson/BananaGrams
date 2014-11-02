package com.apps.hgb.bananagrams;

import android.graphics.Color;
import android.graphics.Point;
import android.view.View;

/**
 * Created by Hunt on 9/28/2014.
 */
public class Game {

    public GameTile[][] gameTiles = new GameTile[100][100];

    public TileManger tileManger; // TODO remove

    public Range xRange;
    public Range yRange;

    public int yStart;
    public int xStart;
    public int xEnd;
    public int yEnd;

    private GameTile SelectedTile = null;

    private GameActivity gameActivity;

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
    public Game(View tileContainer) {
        this.tileManger = new TileManger(tileContainer, this);
        InitializeGraphics();
    }

    // TODO: Implement resume game constructor.  Should call deserialize function.
    public Game(String data)
    {
    }

    private void InitializeGraphics()
    {
        // Create fake game data
        gameTiles = getTiles();

        // Initialize view frame
        this.xStart = 47;
        this.xEnd= 53;
        this.yStart = 47;
        this.yEnd = 53;
    }

    // TODO: get rid of this after done testing
    public GameTile[][] getTiles()
    {
        GameTile gt2 = new GameTile("H", true, true);
        SelectedTile = gt2;
        GameTile gt3 = new GameTile("U", false, true);
        GameTile gt4 = new GameTile("N", false, true);
        GameTile gt5 = new GameTile("T", false, true);

        GameTile[][] gts = new GameTile[100][100];

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                gts[i][j] = new GameTile("", false, false);
            }
        }

        int i= 0;
        int x = i++;
        gts [50][50] = gt2;
        gts [51][51] = gt3;
        gts [50][51] = gt4;
        gts [51][50] = gt5;
        return gts;
    }

    // TODO: Change these to one method with switch later
    // Left
    public void MoveLeft()
    {
        xStart += 1;
        xEnd += 1;
    }

    // right
    public void MoveRight()
    {
        xStart -= 1;
        xEnd -= 1;
    }

    // Down
    public void MoveDown()
    {
        yStart -= 1;
        yEnd -= 1;
    }

    // UP
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
            if (touchedTile.HasLetter)
            {
                //PopTile(touchedTile);
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

    public boolean AddTileToBoard(String letter)
    {
        if (SelectedTile == null)
            return false;

        SelectedTile.letter = letter;
        SelectedTile.HasLetter = true;
        return true;
    }

    //
    //  TODO:  Refactor array to ArrayList later.  Or return coordinates where removed tile is. This is sloppy.
    //
    private void DeleteTile(GameTile tileToRemove)
    {
        for (int i = 0; i < gameTiles.length; i++)
        {
            for (int j = 0; j < gameTiles[i].length; j++)
            {
                if (gameTiles[i][j] == tileToRemove)
                    gameTiles[i][j].SetToEmpty();
            }
        }
    }

    //
    //
    //
    private void unHighlightAll()
    {
        // Set previously selected tile to neutral

        // if selected == null
        //   return
        // else if sel == Tile.Green
        //   sel == neutral
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

    public boolean GameOver()
    {
        return false;
    }
}
