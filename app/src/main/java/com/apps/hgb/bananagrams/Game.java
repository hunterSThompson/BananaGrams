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
    public Game()
    {
        InitializeGraphics();
    }

    // TODO: Implement Game constructor.  Should randomly generate tiles
    public Game(GameActivity gameActivity) {
        this.gameActivity = gameActivity;
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
        unHighlightAll();
        switch (touchedTile.tileStatus) {
            case Empty:
                touchedTile.Touch();
                return;
            case Neutral:
                touchedTile.Touch();
                break;
            case SelectedGreen:
                touchedTile.Touch();
                break;
            case SelectedRed:
                touchedTile.Touch();
                //DeleteTile(touchedTile);
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
