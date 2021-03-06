package com.apps.hgb.bananagrams;

import android.widget.Toast;

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

    public void MoveBoard(Direction direction)
    {
        if (CheckBounds())
            return;

        switch (direction)
        {
            case Up:
                MoveUp();
                return;
            case Down:
                MoveDown();
                return;
            case Left:
                MoveLeft();
                return;
            case Right:
                MoveRight();
                return;
        }
    }

    // todo set nums to constants
    private boolean CheckBounds()
    {
        return GameData.xStart < 1 || GameData.xEnd > 98 || GameData.yStart < 1 || GameData.yEnd > 98;
    }

    private void MoveLeft()
    {
        GameData.xStart += 1;
        GameData.xEnd += 1;
    }

    private void MoveRight()
    {
        GameData.xStart -= 1;
        GameData.xEnd -= 1;
    }

    private void MoveDown()
    {
        GameData.yStart -= 1;
        GameData.yEnd -= 1;
    }

    private void MoveUp()
    {
        GameData.yStart += 1;
        GameData.yEnd += 1;
    }

    //
    //  Handler for a board touch
    //
    public void BoardClick(float x, float y, float height, float width)
    {
        GameTile touchedTile = Utilities.GetTouchedTile(x, y, GameData, height, width);

        // If no tile is selected, select save and cache it.
        if (GameData.SelectedTile == null) {
            touchedTile.Select();
            GameData.SelectedTile = touchedTile;
        }
        else // If a tile is selected...
        {
            // If selected tile is the touched, move it to the tray and the clear the tile.
            if (GameData.SelectedTile == touchedTile)
            {
                if (touchedTile.HasLetter) {
                    PopTile(touchedTile); // Add tile back to tray
                    GameData.CachedTiles.remove(touchedTile); // Remove from occupied tile cache
                }
                touchedTile.ClearTile(); // Clear letter/selection of tile
                GameData.SelectedTile = null;
            }
            // If not...
            else
            {
                GameData.SelectedTile.UnSelect();  // Un-select current selected tile
                GameData.SelectedTile = touchedTile; // Cache the new tile
                GameData.SelectedTile.Select(); // Select the new one
            }
        }
    }


    //
    //  Retrieves the letters that are in the games view frame
    //
    public GameTile[][] GetVisibleLetters() {

        // TODO Add error handling for out of index array. Consider moving to Utils.
        // Should put this within GameData class
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
            String message = e.getMessage();
        }

        return tilesToDraw;
    }

    //
    //  Move tile from Board to Tray
    //
    private void PopTile(GameTile gt)
    {
        gameActivity.PopTile(gt.letter);
    }

    //
    //  Adds a tile to the game board from the tray.
    //  Returns true if successful (if no tile is highlighted then it can't be added)
    //
    public boolean AddTileToBoard(String letter)
    {
        // todo make sure adding tile to tile that already has letter is handled
        if (GameData.SelectedTile == null)
            return false;

        GameData.SelectedTile.SetLetter(letter);
        GameData.CachedTiles.add(GameData.SelectedTile);
        GameData.SelectedTile = null;

        CheckGameState();

        return true;
    }

    public void SaveState()
    {
        if (GameData != null)
            Utilities.Serialize(GameData, gameActivity);
    }

    public void CheckGameState()
    {
        if (!gameActivity.IsTrayEmpty() || GameData.CachedTiles.size() < 1)
            return;

        boolean boardIsValid = Utilities.GameOver(GameData, gameActivity.getResources());
        if (boardIsValid)
        {
            Toast.makeText(gameActivity, "Game Over!! You win.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            // Trigger error in board action
            //Toast.makeText(gameActivity, "Invalid Words", Toast.LENGTH_SHORT).show();
        }
    }
}
