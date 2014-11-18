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
    // could actually make this memeber of Game class
    public void AddTile(String letter)
    {
        letters.add(letter);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return letters.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // TODO Settup inflator. Wayyyyy easier to set properties and such there.

        View grid = new Button(mContext);
        Button t = (Button) grid;
        t.setText(letters.get(position));

        AttachClickListener(t, position);

        return grid;
    }

    private void AttachClickListener(Button b, final int position)
    {
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String letter = ((Button) view).getText().toString();
                if (!game.AddTileToBoard(letter))
                    return;

                ((GameActivity) mContext).InvalidateGameBoard();
                RemoveItem(position);
            }

        });
    }
}