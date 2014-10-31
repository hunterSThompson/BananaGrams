package com.apps.hgb.bananagrams;

import android.view.View;

/**
 * Created by Hunt on 10/29/2014.
 */
public class TileManger {

    private Game game;
    private View tileContainer;

    public TileManger(View tileContainer, Game game)
    {
        this.game = game;
        this.tileContainer = tileContainer;
    }

    //
    // Moves GameTile from board to tray
    //
    public void PopTile(GameTile gt)
    {
        // Get lower-most horizontal layout view

        // Check num of elements

        // if full
            // add horizontal layout
            // add element
        // else
            // add to layout


        /// Maybe should cash lowest row
    }

    //
    // When tile is clicked, remove from tray and add to board
    // if a GameTile is selected
    //
    public void TileClick(View v, String letter)
    {
        // Return if no tile is selected
        // if (!game.CanPlaceNewTile)
        //    return;

        // Get rid of tile in tray

        // Add tile to board
    }

    //
    // Add all tiles to view
    //
    private void InitializeTiles(View v, String data)
    {

        // if data == "" | null
        //      GenerateNewData();
        // else
        //      Deserialize(data);
    }

    private void Deserialize(String data)
    {
        // foreach string
        //    Create a tile with this letter
        //    add it
    }

    private void GenerateNewData()
    {
    }
}
