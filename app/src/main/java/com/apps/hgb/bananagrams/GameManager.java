package com.apps.hgb.bananagrams;

/**
 * Created by Hunt on 10/2/2014.
 */
public class GameManager {

    private GameData gameData;

    public GameManager()
    {
        gameData = new GameData();
    }

    public GameManager(String data)
    {
        gameData = new GameData(data);
    }

    public void BoardClick()
    {
    }

    public void TileClick()
    {
    }

    public void MovementClick(Direction direction)
    {
    }
}

