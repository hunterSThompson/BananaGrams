package com.apps.hgb.bananagrams;

import android.graphics.Point;

/**
 * Created by Hunt on 9/30/2014.
 */
public class InputManager {

    public void HandleMovementTouch(Direction direction, Game game)
    {
        //TODO: change to switch later
        if (direction == Direction.Down)
        {
            game.yStart -= 1;
            game.yEnd -= 1;
        }
        else if (direction == Direction.Up)
        {
            game.yStart += 1;
            game.yEnd += 1;
        }
        else if (direction == Direction.Left)
        {
            game.xStart -= 1;
            game.xEnd -= 1;
        }
        else
        {
            game.xStart += 1;
            game.xEnd += 1;
        }
    }

    public void HandleBoardTouch(float x, float y, Game game) {
    }

    private Point GetTileCords(float x, float y, Game game)
    {
        return null;
    }

}
