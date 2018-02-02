package com.bob.bobmobileapp.menu.viewholders.menunode;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bob.bobmobileapp.BOBApplication;
import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.activities.MainActivity;
import com.bob.bobmobileapp.finals;
import com.bob.bobmobileapp.menu.adapters.MenuNodesAdapter;
import com.bob.bobmobileapp.realm.RealmController;
import com.bob.bobmobileapp.realm.objects.MenuNode;
import com.bob.bobmobileapp.realm.objects.MenuNodeProperty;
import com.bob.bobmobileapp.tools.UI.style.Fonts;
import com.bob.bobmobileapp.tools.UI.style.Icons;
import com.bumptech.glide.Glide;

import java.util.HashMap;

import io.realm.RealmResults;

/**
 * Created by user on 17/09/2017.
 */

public class MenuNodeViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private MenuNodesAdapter adapter;
    private View view;
    private ImageView menuImageView;
    private TextView menuTitleView;
    private long menuNodeId;
    private int fontColor, layoutBackgroundColor, imageColor,
            gravity, imageMaxHeight, imageMaxWidth;
    private float fontSize;
    private Typeface fontType;
    private String title, imageUrl;
    private Drawable layoutBackground, imageDrawable;
    private boolean boldText, underlineText, italicText;

    public MenuNodeViewHolder(Context context, MenuNodesAdapter adapter, View itemView) {
        super(itemView);
        this.context = context;
        this.adapter = adapter;
        this.setView((LinearLayout)itemView.findViewById(R.id.menu_node_view));
        this.setMenuImageView((ImageView)itemView.findViewById(R.id.menu_node_image));
        this.setMenuTitleView((TextView) itemView.findViewById(R.id.menu_node_title));
        this.initialize();
        this.configure();
    }

    public ImageView getMenuImageView() {
        return menuImageView;
    }

    public void setMenuImageView(ImageView menuImageView) {
        this.menuImageView = menuImageView;
    }

    public TextView getMenuTitleView() {
        return menuTitleView;
    }

    public void setMenuTitleView(TextView menuTitleView) {
        this.menuTitleView = menuTitleView;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    private void initialize() {
        this.menuNodeId = 0;
        this.layoutBackgroundColor = ContextCompat.getColor(context, R.color.transparent);
        this.fontColor = ContextCompat.getColor(context, R.color.textColorPrimary);
        this.fontSize = context.getResources().getDimension(R.dimen.text_size_medium);
        this.layoutBackground = null;
        this.fontType = null;
        this.boldText = false;
        this.underlineText = false;
        this.italicText = false;
        this.gravity = finals.gravity.get("center");
        this.imageMaxHeight = (int) context.getResources().getDimension(R.dimen.menu_node_default_size);
        this.imageMaxWidth = (int) context.getResources().getDimension(R.dimen.menu_node_default_size);
        this.title = null;
        this.imageUrl = null;
        this.imageDrawable = null;
        this.imageColor = ContextCompat.getColor(context, R.color.transparent);
    }

    private void updateProperties(HashMap<String, String> properties) {
        String curProperty;
        if ((curProperty = properties.get("id")) != null) {
            try {
                this.menuNodeId = Long.parseLong(curProperty);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("font_color")) != null) {
            try {
                this.fontColor = Color.parseColor(curProperty);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("layout_background_color")) != null) {
            try {
                this.layoutBackgroundColor = Color.parseColor(curProperty);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("font_size")) != null) {
            try {
                this.fontSize = Integer.parseInt(curProperty);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("layout_background")) != null) {
            this.layoutBackground = ContextCompat.getDrawable(context, context.getResources().getIdentifier(curProperty, "drawable", context.getPackageName()));
        }
        if ((curProperty = properties.get("font_type")) != null) {
            this.fontType = Fonts.get().findTypeface(this.context, curProperty);
        }
        if ((curProperty = properties.get("bold_text")) != null) {
            if (curProperty.equals("true")) {
                this.boldText = true;
            } else if (curProperty.equals("false")) {
                this.boldText = false;
            }
        }
        if ((curProperty = properties.get("underline_text")) != null) {
            if (curProperty.equals("true")) {
                this.underlineText = true;
            } else if (curProperty.equals("false")) {
                this.underlineText = false;
            }
        }
        if ((curProperty = properties.get("italic_text")) != null) {
            if (curProperty.equals("true")) {
                this.italicText = true;
            } else if (curProperty.equals("false")) {
                this.italicText = false;
            }
        }
        if ((curProperty = properties.get("gravity")) != null) {
            this.gravity = finals.gravity.get(curProperty);
        }
        if ((curProperty = properties.get("image_height")) != null) {
            try {
                this.imageMaxHeight = Integer.parseInt(curProperty);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("image_width")) != null) {
            try {
                this.imageMaxHeight = Integer.parseInt(curProperty);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("image_size")) != null) {
            try {
                this.imageMaxHeight = Integer.parseInt(curProperty);
                this.imageMaxWidth= Integer.parseInt(curProperty);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("title")) != null) {
            this.title = curProperty;
        }
        if ((curProperty = properties.get("image_url")) != null) {
            this.imageUrl = curProperty;
        }
        if ((curProperty = properties.get("image_drawable")) != null) {
            this.imageDrawable = Icons.get().findDrawable(this.context, curProperty);
        }
        if ((curProperty = properties.get("image_color")) != null) {
            try {
                this.imageColor = Color.parseColor(curProperty);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    private void configure() {
        this.view.setBackground(this.layoutBackground);
        this.view.setBackgroundColor(this.layoutBackgroundColor);
        this.menuTitleView.setGravity(this.gravity);
        this.menuTitleView.setTextColor(this.fontColor);
        this.menuTitleView.setTextSize(this.fontSize);
        this.menuTitleView.setTypeface(this.fontType);
        if (this.italicText && this.boldText) {
            this.menuTitleView.setTypeface(this.menuTitleView.getTypeface(), Typeface.BOLD_ITALIC);
        } else {
            if (this.boldText) {
                this.menuTitleView.setTypeface(this.menuTitleView.getTypeface(), Typeface.BOLD);
            }
            if (this.italicText) {
                this.menuTitleView.setTypeface(this.menuTitleView.getTypeface(), Typeface.ITALIC);
            }
        }
        if (this.underlineText) {
            this.menuTitleView.setPaintFlags(this.menuTitleView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        }
        this.menuTitleView.setText(this.title);
        this.menuImageView.setMaxHeight(this.imageMaxHeight);
        this.menuImageView.setMaxWidth(this.imageMaxWidth);
        if (this.imageUrl != null) {

            Glide.with(this.context)
                    .load(Uri.parse(this.imageUrl))
                    .into(this.menuImageView);
        }
        if (this.imageDrawable != null) {
            menuImageView.setImageDrawable(this.imageDrawable);
        }
        if (imageColor != ContextCompat.getColor(this.context, R.color.transparent)) {
            menuImageView.getDrawable().setColorFilter(new PorterDuffColorFilter(this.imageColor, PorterDuff.Mode.SRC_IN));
        }
        this.menuImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) context).setCurMenuNode(menuNodeId);
            }
        });
    }

    public void configureMenuNode(MenuNode menuNode) {
        HashMap<String, String> properties = new HashMap<String, String>();
        RealmResults<MenuNodeProperty> RealmProperties = RealmController.get().with(BOBApplication.get()).getPropertiesOfMenuNode(menuNode.getId());
        properties.put("id", String.valueOf(menuNode.getId()));
        properties.put("title", menuNode.getTitle());
        properties.put("image_url", menuNode.getImageUrl());
        for (MenuNodeProperty property : RealmProperties) {
            properties.put(property.getKey(), property.getValue());
        }
        updateProperties(properties);
        configure();
    }


}
