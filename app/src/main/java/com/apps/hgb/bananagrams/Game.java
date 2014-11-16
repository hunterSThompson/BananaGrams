package com.apps.hgb.bananagrams;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hunt on 9/28/2014.
 */
// TODO Organize methods/members into correct sections
public class Game {

    /********************************************************************
     * Members
     ********************************************************************/

    public GameData GameData;
    private GameActivity gameActivity;

    /********************************************************************
     * Constructor
    ********************************************************************/

    //
    //  Pass continuing game as true if resuming.
    //
    public Game(GameActivity gameActivity, Boolean continuingGame)
    {
        this.gameActivity = gameActivity;
        if (continuingGame)
            LoadSavedState();
        else
            InitializeGameData();
    }


    /********************************************************************
     * Public Methods
     ********************************************************************/
    private void InitializeGameData()
    {
        GameData = new GameData();
        InitializeTiles();

        // Initialize view frame
        GameData.xStart = 50 - Constants.TileGridLength/ 2;
        GameData.xEnd = 50 + Constants.TileGridLength / 2;
        GameData.yStart = 50 - Constants.TileGridLength / 2;
        GameData.yEnd = 50 + Constants.TileGridLength / 2;
    }

    private void LoadSavedState()
    {
        GameData = Utilities.Deserialize(gameActivity);

        if (GameData == null)
            InitializeGameData();
    }

    private void InitializeTiles()
    {
        GameTile[][] gts = new GameTile[Constants.boardWidth][Constants.boardHeight];

        for (int i = 0; i < Constants.boardWidth; i++) {
            for (int j = 0; j < Constants.boardHeight; j++) {
                gts[i][j] = new GameTile(i, j);
            }
        }

        GameData.gameTiles = gts;
    }

    // TODO: Change these one method with switch later
    public void MoveLeft()
    {
        GameData.xStart += 1;
        GameData.xEnd += 1;
    }

    public void MoveRight()
    {
        GameData.xStart -= 1;
        GameData.xEnd -= 1;
    }

    public void MoveDown()
    {
        GameData.yStart -= 1;
        GameData.yEnd -= 1;
    }

    public void MoveUp()
    {
        GameData.yStart += 1;
        GameData.yEnd += 1;
    }

    public void BoardClick(float x, float y, float height, float width)
    {
        GameTile touchedTile = Utilities.GetTouchedTile(x, y, this, height, width);

        if (GameData.SelectedTile == null) {
            touchedTile.Select();
            GameData.SelectedTile = touchedTile;
        }
        else
        {
            if (GameData.SelectedTile == touchedTile) {
                //PopTile(touchedTile);
                touchedTile.ClearTile();
            }
            else {
                GameData.SelectedTile.UnSelect();
            }
            GameData.SelectedTile = null;
        }

        //boolean g = GameOver();
    }

    // TODO Add error handling for out of index array
    public GameTile[][] GetVisibleLetters() {

        GameTile[][] tilesToDraw = new GameTile[Constants.TileGridLength][Constants.TileGridLength];

        int numX = 0;
        int numY = 0;

        int difX = Math.abs(GameData.xStart - GameData.xEnd);
        int difY = Math.abs(GameData.yStart - GameData.yEnd);

        if (difX != 5 || difY != 5)  // change 5 to constants.BoardSize
        {
            // TODO: The app should crash if our view frame isn't the right num of things...
        }

        try
        {
            for (int i = GameData.xStart; i < GameData.xEnd; i++)
            {
                for (int j = GameData.yStart; j < GameData.yEnd; j++)
                {
                    tilesToDraw[numX][numY] = GameData.gameTiles[i][j];
                    numY++;
                }
                numX++;
                numY = 0;
            }
        }
        catch(Exception e)
        {
            String mess = e.getMessage();
        }

        return tilesToDraw;
    }

    //
    //  Move tile from Board to Tray
    //
    private void PopTile(GameTile gt)
    {
        //gameActivity.PopTile();
        GameData.CachedTiles.remove(gt);
    }

    //
    //  Adds a tile to the game board from the tray.
    //  Returns true if successful (if no tile is highlighted then it can't be added)
    //
    public boolean AddTileToBoard(String letter)
    {
        if (GameData.SelectedTile == null)
            return false;

        GameData.SelectedTile.SetLetter(letter);
        GameData.CachedTiles.add(GameData.SelectedTile);
        GameData.SelectedTile = null;

        GameOver();

        return true;
    }

    public void SaveState()
    {
        if (GameData != null)
            Utilities.Serialize(GameData, gameActivity);
    }

    // TODO: Implement. Move to Utils.  Also get rid of count. Is just for testing GameOver func.
    int count = 0;
    public void GameOver()
    {
        if (gameActivity.IsTrayEmpty() || GameData.CachedTiles.size() < 1)
            return;

        count++;

        boolean boardIsValid = Utilities.GameOver(GameData);
        if (boardIsValid && count > 4)
        {
            Toast.makeText(gameActivity, "Game Over!! You win.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            // Trigger error in board action
        }
    }
}
