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

    private void SetOldState(GameTile gametile)
    {
        OldState = new GameTile(gametile);
    }

    private void SetNewState(GameTile gametile)
    {
        NewState = new GameTile(gametile);
    }

    // Change to take GameData. Can't serialize this otherwise due to circular reference.
    public void Forward()
    {
    }

    public void Backward()
    {
        //if (OldState == null)
            //throw new Exception();

        GameTile tile;

        // Get tile by cords of Old State
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
