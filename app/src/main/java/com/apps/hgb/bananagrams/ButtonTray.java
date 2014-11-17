package com.apps.hgb.bananagrams;

import android.content.Context;
import android.view.LayoutInflater;
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
    //private List<String> web;

    List<String> web = new ArrayList<String>(Arrays.asList(
            Constants.Letters
    ));

    public void RemoveItem(int pos)
    {
        web.remove(pos);
        this.notifyDataSetChanged();
    }

    public void AddTile(String letter)
    {
        web.add(letter);
        this.notifyDataSetChanged();
    }

    public ButtonTray(Context c, Game game) {
        mContext = c;
        this.game = game;

        web = new ArrayList<String>(Arrays.asList(
                Utilities.ShuffleGameLetters()
        ));
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return web.size();
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

        // TODO Auto-generated method stub
        View grid;
        //LayoutInflater inflater = (LayoutInflater) mContext
                //.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //grid = new View(mContext);
        grid = new TextView(mContext);
        TextView t = (TextView) grid;
        //Button b = (Button) grid;

        t.setText(web.get(position));
        //t.setText(web.get(position));

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String letter = ((TextView) view).getText().toString();
                if (!game.AddTileToBoard(letter))
                    return;

                ((GameActivity) mContext).InvalidateGameBoard();
                web.remove(position);
                bt.notifyDataSetChanged();
            }
        });

        return grid;
    }
}