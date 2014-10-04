package com.apps.hgb.bananagrams;

import android.graphics.Color;
import android.graphics.Point;

/**
 * Created by Hunt on 9/28/2014.
 */
public class Game {

    public GameTile[][] gameTiles = new GameTile[100][100];

    public String[] selectableTiles = new String[50];

    public Range xRange;
    public Range yRange;

    public int yStart;
    public int xStart;
    public int xEnd;
    public int yEnd;

    public GameTile[][] GetVisibleLetters() {

        GameTile[][] tilesToDraw = new GameTile[6][6];

        int numX = 0;
        int numY = 0;

        int difX = Math.abs(this.xStart - this.xEnd);
        int difY = Math.abs(this.yStart - this.yEnd);

        if (difX != 5 || difY != 5)
        {
            // TODO: The app should crash if our view frame isn't the right num of things...
            // that would be very bad...
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
    public Game()
    {
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
        // TODO: cleanthis up

        GameTile gt1 = new GameTile("", TileStatus.Empty);
        GameTile gt2 = new GameTile("H", TileStatus.Neutral);
        GameTile gt3 = new GameTile("U", TileStatus.Empty);
        GameTile gt4 = new GameTile("N", TileStatus.Empty);
        GameTile gt5 = new GameTile("T", TileStatus.Empty);
        GameTile gt6 = new GameTile("E", TileStatus.Empty);

        GameTile[][] gts = new GameTile[100][100];

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                gts[i][j] = new GameTile("", TileStatus.Empty);
            }
        }

        int i= 0;
        int x = i++;
        gts [50][50] = gt2;
        return gts;
    }

    // TODO: Change these to one method with switch later
    public void MoveUp()
    {
        xStart += 1;
        xEnd += 1;
    }

    public void MoveDown()
    {
        xStart -= 1;
        xEnd -= 1;
    }

    public void MoveRight()
    {
        yStart -= 1;
        yEnd -= 1;
    }

    public void MoveLeft()
    {
        yStart += 1;
        yEnd += 1;
    }

    public void BoardClick(float x, float y, float height, float width)
    {
        GameTile touchedTile = Utilities.GetTouchedTile(x, y, this, height, width);
        switch (touchedTile.tileStatus) {
            case Empty:
                unHighlightAll();
                touchedTile.Touch();
                return;
            case Neutral:
                unHighlightAll();
                touchedTile.Touch();
                break;
            case SelectedGreen:
                touchedTile.Touch();
                break;
            case SelectedRed:
                DeleteTile(touchedTile);
                return;
        };
        //Utilities.GetTouchedTile2(x, y, this, height, width);
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
                    gameTiles[i][j].setToEmpty();
            }
        }
    }

    private void unHighlightAll()
    {
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
