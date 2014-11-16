package com.apps.hgb.bananagrams;

import android.content.Context;
import android.content.res.Resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.OutputStreamWriter;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    public static boolean LookUp(String word, Resources res)
    {
        /*
        char firstLetter = 'a'; //= word.fir
        switch(firstLetter)
        {
            case 'a':
                break;
        }
        */

        try
        {
            InputStream in_s = res.openRawResource(R.raw.wordlist);
            byte[] b = new byte[in_s.available()];
            in_s.read(b);
            String s = new String(b);

            String[] words = s.split("\n");

            int index = Arrays.binarySearch(words, word.toLowerCase());

            if (index > -1)
                return true;
            else
                return false;

        }
        catch (Exception e)
        {
            // TODO: add logging here
            return false;
        }
    }

    public static boolean GameOver(GameData GameData, Resources resources)
    {
        List<GameTile> verticals = new ArrayList<GameTile>();
        List<GameTile> horizontal = new ArrayList<GameTile>();

        // Find each starting Tile of Vertical/Horizontal words
        GameTile above, below, left, right;
        for (GameTile gameTile : GameData.CachedTiles)
        {
            above = GetNeighborTile(GameData, gameTile, Direction.Up);
            below = GetNeighborTile(GameData, gameTile, Direction.Down);
            left = GetNeighborTile(GameData, gameTile, Direction.Left);
            right = GetNeighborTile(GameData, gameTile, Direction.Right);

            if (!above.HasLetter && below.HasLetter)
                verticals.add(gameTile);

            if (!left.HasLetter && right.HasLetter)
                horizontal.add(gameTile);
        }

        // TODO move some of these methods to Board class after creating it.  Should rap GameTiles[i][j]

        for (GameTile gameTile : verticals)
        {
            String word = Utilities.getLetters(gameTile, GameData, Direction.Down);
            boolean validWord = Utilities.LookUp(word, resources);
            if (!validWord)
                return false;
        }

        for (GameTile gameTile : horizontal)
        {
            String word = Utilities.getLetters(gameTile, GameData, Direction.Right);
            boolean validWord = Utilities.LookUp(word, resources);
            if (!validWord)
                return false;
        }

        return true;
    }

    //
    //
    //
    static public GameTile GetNeighborTile(GameData gameData, GameTile gameTile, Direction direction)
    {

        int X = gameTile.X;
        int Y = gameTile.Y;

        boolean xInBound = (X >= 0) && (X < 100);
        boolean yInBound = (Y >= 0) && (Y < 100);

        if (!xInBound || !yInBound)
            return null;

        try
        {
            switch (direction)
            {
                case Down:
                    return gameData.gameTiles[gameTile.X][gameTile.Y + 1];
                case Up:
                    return gameData.gameTiles[gameTile.X][gameTile.Y - 1];
                case Left:
                    return gameData.gameTiles[gameTile.X - 1][gameTile.Y];
                case Right:
                    return gameData.gameTiles[gameTile.X + 1][gameTile.Y];
                default:
                    return null;
            }

            /*
            if (direction == Direction.Down)
            {
                return gameData.gameTiles[gameTile.X][gameTile.Y + 1];
            }
            else if (direction == Direction.Up)
            {
                return gameData.gameTiles[gameTile.X][gameTile.Y - 1];
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
            */
        }
        catch (Exception e)
        {
            String message = e.getMessage();
        }
        return null;
    }

   static public String getLetters(GameTile target, GameData gameData, Direction direction)
   {
       String word = target.letter;
       GameTile tileToTheRight = target;
       int i = 0;
       while (tileToTheRight == null || tileToTheRight.HasLetter)
       {
           tileToTheRight = GetNeighborTile(gameData, tileToTheRight, direction);
           word += tileToTheRight.letter;
       }
       return word;
   }
}
