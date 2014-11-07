package com.apps.hgb.bananagrams;

import android.content.Context;

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

    // TODO Add error handling for out of index array
    public GameTile[][] GetVisibleLetters() {

        GameTile[][] tilesToDraw = new GameTile[6][6];

        int numX = 0;
        int numY = 0;

        int difX = Math.abs(GameData.xStart - GameData.xEnd);
        int difY = Math.abs(GameData.yStart - GameData.yEnd);

        if (difX != 5 || difY != 5)
        {
            // TODO: The app should crash if our view frame isn't the right num of things...
        }

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

        return tilesToDraw;
    }

    // TODO: Should randomly generate tiles
    public Game(GameActivity gameActivity) {
        this.gameActivity = gameActivity;
        InitializeGameData();
    }

    // TODO: Refactor to different functions
    public Game(GameActivity gameActivity, Boolean continuingGame)
    {
        this.gameActivity = gameActivity;
        if (continuingGame)
            InitializeGameData("");
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
        GameData.xStart = 47;
        GameData.xEnd= 53;
        GameData.yStart = 47;
        GameData.yEnd = 53;
    }

    private void InitializeGameData(String data)
    {
        GameData = Utilities.Deserialize(data, gameActivity);

        if (GameData == null)
            InitializeGameData();
    }

    private void InitializeTiles()
    {
        GameTile[][] gts = new GameTile[100][100];

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                gts[i][j] = new GameTile();
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
            touchedTile.Selected = true;
            GameData.SelectedTile = touchedTile;
        }
        else
        {
            if (GameData.SelectedTile == touchedTile)
            {
                PopTile(touchedTile);
                touchedTile.letter = "";
                touchedTile.Selected = false;
                touchedTile.HasLetter = false;
                //todo remove from cached tiles
            }
            else
            {
                GameData.SelectedTile.Selected = false;
            }
            GameData.SelectedTile = null;
        }
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

        GameData.SelectedTile.letter = letter;
        GameData.SelectedTile.HasLetter = true;
        GameData.SelectedTile.Selected = false;
        GameData.SelectedTile = null;

        // todo Add to cache tiles
        GameData.CachedTiles.add(GameData.SelectedTile);

        Utilities.Serialize(GameData, gameActivity);

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

    // TODO: Implement. Move to Utils
    public boolean GameOver()
    {
        //if (gameActivity.IsTrayEmpty())
        //    return false;

        for (int i = 0; i < GameData.CachedTiles.size(); i++)
        {
            // Get Above Tile
            // Get Below Tile

            // If Above.Empty && Below.HasLetter
            //      while (tileBelow != empty)
            //           letter.add
            //      words.add(word)

            // If Left.Empty && Right.HasLetter
            //      while (tileRight != empty)
            //           letter.add
            //      words.add(word)
        }
        return false;
    }
}
