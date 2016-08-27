package com.androidsummit.androidsummitsampleapp.apimenu;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidsummit.androidsummitsampleapp.R;

import java.util.List;

/**
 * An adapter that sets up items for the ApiMenuActivity's GridView.
 */
public class ApiMenuGridAdapter extends BaseAdapter {

    private static final String TAG = ApiMenuGridAdapter.class.getSimpleName();

    private Context mContext;
    private List<ApiMenuItem> mApiMenuItemList;

    public ApiMenuGridAdapter(Context context, List<ApiMenuItem> apiMenuItemList) {
        mContext = context;
        mApiMenuItemList = apiMenuItemList;
    }

    @Override
    public int getCount() {
        return mApiMenuItemList.size();
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
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view;

        //if (convertView == null) {

            // inflate the grid view
            view = new View(mContext);
            view = inflater.inflate(R.layout.api_menu_grid_item, null);

            // populate the gridview
            TextView apiTitleTextView = (TextView) view.findViewById(R.id.api_tile_title);
            apiTitleTextView.setText(mApiMenuItemList.get(position).getTitle());

            ImageView apiImageTextView = (ImageView) view.findViewById(R.id.api_tile_image);
            apiImageTextView.setImageResource(mApiMenuItemList.get(position).getImageResource());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String className = mApiMenuItemList.get(position).getNavigationClass();
                        if (className != null) {
                            Intent intent = new Intent(mContext, Class.forName(mApiMenuItemList.get(position).getNavigationClass()));
                            mContext.startActivity(intent);
                        }
                    } catch (ClassNotFoundException e) {
                        Log.e(TAG, "Class for item not found.");
                    }

                }
            });
        /*} else {
            view = convertView;
        }*/

        return view;
    }
}
