package com.matheducation.mazejava;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

public class GridAdapter extends BaseAdapter {
    private Context context;
    private int[][] matrix;
    private int coloredPosition = -1;  // Initialize with -1 to indicate no item should be colored by default

    public void setColoredPosition(int position) {
        this.coloredPosition = position;
        notifyDataSetChanged();  // Notify the adapter that the data has changed
    }
    public GridAdapter(Context context, int[][] matrix) {
        this.context = context;
        this.matrix = matrix;
    }

    @Override
    public int getCount() {
        return matrix.length * matrix[0].length;
    }

    @Override
    public Object getItem(int position) {
        int row = position / matrix[0].length;
        int col = position % matrix[0].length;
        return matrix[row][col];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView == null) {
            textView = new TextView(context);
            textView.setLayoutParams(new GridView.LayoutParams(100, 100));
        } else {
            textView = (TextView) convertView;
        }

        // Check if the current position matches the colored position
        if (position == coloredPosition) {
            textView.setBackgroundColor(ContextCompat.getColor(context, R.color.teal_200));
        } else {
            textView.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        }

        int value = (int) getItem(position);
        textView.setText(String.valueOf(value));

        return textView;
    }


}

