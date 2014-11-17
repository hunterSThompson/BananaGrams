package com.apps.hgb.bananagrams;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Hunt on 11/16/2014.
 */
public class ButtonTray extends BaseAdapter {

    private Context mContext;
    private Game game;
    List<String> letters;

    public ButtonTray(Context c, Game game) {
        mContext = c;
        this.game = game;

        letters = new ArrayList<String>(Arrays.asList(
                Utilities.ShuffleGameLetters()
        ));
    }

    public void RemoveItem(int pos)
    {
        letters.remove(pos);
        this.notifyDataSetChanged();
    }

    // todo write methods so this data is serialized on save / de-serialized and loaded

    public void AddTile(String letter)
    {
        letters.add(letter);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return letters.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    ButtonTray bt = this;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // TODO Settup inflator. Wayyyyy easier to set properties and such there.
        View grid;

        //
        //LayoutInflater inflater = (LayoutInflater) mContext
                //.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //

        /*
        Button buyButton = new Button(mContext);
        buyButton.setText(letters.get(position));
        buyButton.setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        */

        grid = new TextView(mContext);
        TextView t = (TextView) grid;
        //Button b = (Button) grid;

        t.setText(letters.get(position));
        //t.setText(letters.get(position));

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String letter = ((TextView) view).getText().toString();
                if (!game.AddTileToBoard(letter))
                    return;

                ((GameActivity) mContext).InvalidateGameBoard();
                letters.remove(position);
                bt.notifyDataSetChanged();
            }
        });

        return grid;
    }
}