package com.bob.bobmobileapp.drawerItems;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bob.bobmobileapp.R;

public class CustomBaseViewHolder extends RecyclerView.ViewHolder {
    public View view;
    public ImageView icon;
    public TextView name;
    public TextView description;

    public CustomBaseViewHolder(View view) {
        super(view);

        this.view = view;
        this.icon = (ImageView) view.findViewById(R.id.material_drawer_icon);
        this.name = (TextView) view.findViewById(R.id.material_drawer_name);
        this.description = (TextView) view.findViewById(R.id.material_drawer_description);
    }
}