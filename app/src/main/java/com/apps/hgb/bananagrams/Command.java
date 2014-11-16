package com.apps.hgb.bananagrams;

import java.io.Serializable;

/**
 * Created by Hunt on 11/16/2014.
 */
public class Command implements Serializable {

    private GameData gameData;
    private GameTile OldState;
    private GameTile NewState;

    // May need to create 'TileState' class to wrap cords
    public Command(GameData gameData)
    {
        this.gameData = gameData;
    }

    public void SetOldState(GameTile gametile)
    {
        OldState = new GameTile(gametile);
    }

    // Most likely will never use this.
    public void SetNewState(GameTile gametile)
    {
        NewState = new GameTile(gametile);
    }

    // Change to take GameData. Can't serialize this otherwise due to circular reference.
    public void Forward()
    {
        if (NewState == null || gameData == null)
            return;

        GameTile tile;

        try
        {
            tile = gameData.gameTiles[OldState.X][OldState.Y];
            tile.LoadState(OldState);
        }
        catch (Exception e)
        {
        }
    }

    public void Backward()
    {
        if (OldState == null)
            return;

        GameTile tile;

        // Get tile by cords of OldState and load former state
        try
        {
            tile = gameData.gameTiles[OldState.X][OldState.Y];
            tile.LoadState(OldState);
        }
        catch (Exception ignored)
        {
        }
    }


}
