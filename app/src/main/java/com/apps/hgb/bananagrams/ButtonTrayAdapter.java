package com.apps.hgb.bananagrams;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Hunt on 11/5/2014.
 */
public class ButtonTrayAdapter extends BaseAdapter {

    private Context mContext;
    private final String[] web;

    public ButtonTrayAdapter(Context c, String[] letters)
    {
        mContext = c;
        this.web = letters;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        if (view == null)
        {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
            //b = new Button(mContext);
            //b.setText("H");
        }
        else
        {
            return view;
        }
        imageView.setImageResource(R.drawable.ic_launcher);
        return imageView;
    }
}
