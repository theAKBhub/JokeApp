package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.android.libjokeviewer.Utils;

public class CategoryAdapter extends BaseAdapter {

    private Context mContext;
    private String[] mJokeCategories;


    /**
     * Default constructor
     */
    public CategoryAdapter(Context context, String[] jokeCategories) {
        mContext = context;
        mJokeCategories = jokeCategories;
    }

    @Override
    public int getCount() {
        return mJokeCategories.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * This class describes the view items to create a list item
     */
    public static class CategoryViewHolder {

        @BindView(R.id.grid_joke_image)
        ImageView imageJokeIcon;

        @BindView(R.id.grid_joke_category)
        TextView textJokeCategory;

        public CategoryViewHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CategoryViewHolder holder;
        View gridView;
        String jokeCategory;

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView != null) {
            holder = (CategoryViewHolder) convertView.getTag();
            gridView = convertView;
        } else {
            gridView = new GridView(mContext);
            gridView = inflater.inflate(R.layout.item_grid, null);
            holder = new CategoryViewHolder(gridView);
            gridView.setTag(holder);

            jokeCategory = Utils.convertStringToFirstCapital(mJokeCategories[position]);
            holder.textJokeCategory.setText(jokeCategory);
            holder.imageJokeIcon.setImageResource(getJokeIcon(jokeCategory));
        }

        return gridView;
    }

    /**
     * Method to display joke category icon based on the category
     *
     * @return joke image drawable ID
     */
    private int getJokeIcon(String category) {
        if (category.equals(mContext.getString(R.string.joke_daily))) {
            return R.drawable.ic_daily;
        } else if (category.equals(mContext.getString(R.string.joke_technology))) {
            return R.drawable.ic_technology;
        } else if (category.equals(mContext.getString(R.string.joke_work))) {
            return R.drawable.ic_work;
        } else if (category.equals(mContext.getString(R.string.joke_animal))) {
            return R.drawable.ic_animal;
        } else if (category.equals(mContext.getString(R.string.joke_health))) {
            return R.drawable.ic_health;
        } else if (category.equals(mContext.getString(R.string.joke_success))) {
            return R.drawable.ic_success;
        } else {
            return R.drawable.ic_daily;
        }
    }
}
