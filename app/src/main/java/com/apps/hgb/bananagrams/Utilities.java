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

    //TODO refactor to take GameData instead of Game object. sloppy
    static GameTile GetTouchedTile(float x, float y, Game game, float height, float width)
    {
        int n = Constants.TileGridLength;
        float cellWidth = width / n;
        float cellHeight = height / n;

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

    //
    //
    //
    static public GameTile GetNeighborTile(GameData gameData, GameTile gameTile, Direction direction) {
        int X = gameTile.X;
        int Y = gameTile.Y;

        boolean xInBound = (X >= 0) && (X < 100);
        boolean yInBound = (Y >= 0) && (Y < 100);

        if (!xInBound || !yInBound)
            return null;

        if (direction == Direction.Down)
        {
            return gameData.gameTiles[gameTile.X][gameTile.Y - 1];
        }
        else if (direction == Direction.Up)
        {
            return gameData.gameTiles[gameTile.X][gameTile.Y + 1];
        }
        else if (direction == Direction.Left)
        {
            return gameData.gameTiles[gameTile.X - 1][gameTile.Y];
        }
        else if (direction == Direction.Right)
        {
            return gameData.gameTiles[gameTile.X + 1][gameTile.Y];
        }
        else return null;
    }

   static public String getLetters(GameTile target, GameData gameData, Direction direction)
   {
       String word = target.letter;
       GameTile tileToTheRight;
       while (true)
       {
           tileToTheRight = GetNeighborTile(gameData, target, direction);
           if (tileToTheRight != null || tileToTheRight.HasLetter)
                word += tileToTheRight.letter;
           else
               break;
       }
       return word;
   }
}
