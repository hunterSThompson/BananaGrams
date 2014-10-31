package com.apps.hgb.bananagrams;

import android.content.Context;
import android.view.View;
import android.widget.Button;

/**
 * Created by Hunt on 10/30/2014.
 */
public class ButtonTile extends Button {

    public TileManger tileManger;

    public ButtonTile(Context context,TileManger tileManger, String letter)
    {
        super(context);
        this.setText(letter);
    }

    private void setClickHandler()
    {
        this.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Perform action on click
                TileManger newtile = tileManger;
            }
        });
    }

    public ButtonTile(Context context) {
        super(context);


    }
}
