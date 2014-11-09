package com.apps.hgb.bananagrams;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.OutputStreamWriter;
import java.io.StreamCorruptedException;
import java.util.Random;

/**
 * Created by Hunt on 10/2/2014.
 */
public final class Utilities {

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

    public static GameData Deserialize(Context context)
    {
        FileInputStream fis;
        ObjectInputStream is;
        GameData gameData = null;

        try {
            fis = context.openFileInput(Constants.ResumeFile);
            is = new ObjectInputStream(fis);
            gameData = (GameData) is.readObject();
            is.close();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
        catch (OptionalDataException e)
        {
            e.printStackTrace();
            return null;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
        catch (StreamCorruptedException e)
        {
            e.printStackTrace();
            return null;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }

        return gameData;
    }

    public static boolean Serialize(GameData gameData, Context context)
    {
        FileOutputStream fos;
        ObjectOutputStream os;

        try
        {
            fos = context.openFileOutput(Constants.ResumeFile, Context.MODE_PRIVATE);
            os = new ObjectOutputStream(fos);
            os.writeObject(gameData);
            os.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return false;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
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
