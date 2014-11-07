package com.apps.hgb.bananagrams;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;

/**
 * Created by Hunt on 10/2/2014.
 */
public final class Utilities {

    static GameTile GetTouchedTile2(float x, float y, Game game, float height, float width)
    {
        int xf = game.GameData.xStart + (int) (x / width / 6);
        int yf = game.GameData.yStart + (int) (y / height / 6);
        GameTile gt = game.GameData.gameTiles[xf][yf];
        return game.GameData.gameTiles[xf][yf];
    }

    //TODO put this method in game class. Maybe refactor to use rect.Contains(x, y)
    static GameTile GetTouchedTile(float x, float y, Game game, float height, float width)
    {
        float cellWidth = width / 6;
        float cellHeight = height / 6;

        float tileX = x / cellWidth;
        float tileY = y / cellHeight;

        int xTileNum = (int) tileX;
        int yTileNum = (int) tileY;

        int xf = xTileNum + game.GameData.xStart;
        int yf = yTileNum + game.GameData.yStart;

        // TODO:  Throw WTF error here
        if ((xf | yf) > 100)
        {
        }
        if ((xf | yf) < 0)
        {
        }

        GameTile gt = game.GameData.gameTiles[xf][yf];
        return game.GameData.gameTiles[xf][yf];
    }

    //
    //  Randomly Scrabble letters
    //
    public static String[] ShuffleGameLetters()
    {
        String[] ar = Constants.Letters;
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            String a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }

        return ar;
    }

    public static GameData Deserialize(String data)
    {
        return null;
    }

    public static String Serialize(GameData gameData)
    {
        String filename = Constants.ResumeFile;
        FileOutputStream fos = null;
        //ObjectOutputStream out = null;
        /*
        try {
            fos = new FileOutputStream(Constants.ResumeFile);
            out = new ObjectOutputStream(fos);
            out.writeObject(gameData);

            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        };
        */

        // Write to file
        try
        {
            //OutputStreamWriter out
                    //= new OutputStreamWriter(openFileOutput(Constants.ResumeFile, MODE_PRIVATE));
            //out.write("");
            //out.close();
        }
        catch (Throwable t) {
            //Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG)
                    //.show();
        }

        return "";
    }

    //
    //
    //
    public static boolean LookUp(String word)
    {
        char firstLetter = 'a'; //= word.fir
        switch(firstLetter)
        {
            case 'a':
                break;
        }
        return true;
    }
}
