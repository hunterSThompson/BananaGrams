package com.apps.hgb.bananagrams;

import android.content.Context;

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
        GameData.xStart = Constants.newGameXStart;
        GameData.xEnd= Constants.newGameXEnd;
        GameData.yStart = Constants.newGameYStart;
        GameData.yEnd = Constants.newGameYEnd;
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
            }
            else
            {
                GameData.SelectedTile.Selected = false;
            }
            GameData.SelectedTile = null;
        }
    }

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

        GameData.CachedTiles.add(GameData.SelectedTile);
        return true;
    }

    public void SaveState()
    {
        if (GameData != null)
            Utilities.Serialize(GameData, gameActivity);
    }

    // TODO: Implement. Move to Utils
    public boolean GameOver()
    {
        //if (gameActivity.IsTrayEmpty())
        //    return false;

        List<GameTile> verticals = new ArrayList<GameTile>();
        List<GameTile> horizontal = new ArrayList<GameTile>();

        // Find each starting Tile of Vertical/Horizontal words
        GameTile above, below, left, right;
        for (GameTile gameTile : GameData.CachedTiles)
        {
            above = Utilities.GetNeighborTile(GameData, gameTile, Direction.Up);
            below = Utilities.GetNeighborTile(GameData, gameTile, Direction.Down);
            left = Utilities.GetNeighborTile(GameData, gameTile, Direction.Left);
            right = Utilities.GetNeighborTile(GameData, gameTile, Direction.Right);

            if (above == null && below != null)
                verticals.add(gameTile);

            if (left == null && right != null)
                horizontal.add(gameTile);
        }

        for (GameTile gameTile : verticals)
        {
            String word = Utilities.getLetters(gameTile, GameData, Direction.Down);
            boolean validWord = Utilities.LookUp(word);
            if (!validWord)
                return false;
        }

        for (GameTile gameTile : horizontal)
        {
            String word = Utilities.getLetters(gameTile, GameData, Direction.Right);
            boolean validWord = Utilities.LookUp(word);
            if (!validWord)
                return false;
        }

        return true;
    }
}
