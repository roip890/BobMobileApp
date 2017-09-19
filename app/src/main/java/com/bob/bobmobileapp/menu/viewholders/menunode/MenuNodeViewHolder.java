package com.bob.bobmobileapp.menu.viewholders.menunode;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.finals;
import com.bob.bobmobileapp.menu.adapters.MenuNodesAdapter;
import com.bob.bobmobileapp.realm.RealmController;
import com.bob.bobmobileapp.realm.objects.FormItem;
import com.bob.bobmobileapp.realm.objects.FormItemProperty;
import com.bob.bobmobileapp.realm.objects.MenuNode;
import com.bob.bobmobileapp.realm.objects.MenuNodeProperty;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.devicon_typeface_library.DevIcon;
import com.mikepenz.entypo_typeface_library.Entypo;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.foundation_icons_typeface_library.FoundationIcons;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.mikepenz.meteocons_typeface_library.Meteoconcs;
import com.mikepenz.octicons_typeface_library.Octicons;
import com.mikepenz.pixeden_7_stroke_typeface_library.Pixeden7Stroke;
import com.mikepenz.typeicons_typeface_library.Typeicons;
import com.mikepenz.weather_icons_typeface_library.WeatherIcons;
import com.squareup.picasso.Picasso;
import com.vstechlab.easyfonts.EasyFonts;

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
    private long memuNodeId;
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
        setView((LinearLayout)itemView.findViewById(R.id.menu_node_view));
        setMenuImageView((ImageView)itemView.findViewById(R.id.menu_node_image));
        setMenuTitleView((TextView) itemView.findViewById(R.id.menu_node_title));
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
        this.memuNodeId = -1;
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
                this.memuNodeId = Long.parseLong(curProperty);
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
            this.fontType = this.findTypeface(curProperty);
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
            this.imageDrawable = findDrawable(curProperty);
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
        Picasso.with(this.context).load(this.imageUrl).into(this.menuImageView);
        menuImageView.setImageDrawable(this.imageDrawable);
        if (imageColor != ContextCompat.getColor(this.context, R.color.transparent)) {
            menuImageView.getDrawable().setColorFilter(new PorterDuffColorFilter(this.imageColor, PorterDuff.Mode.SRC_IN));
        }
        this.menuImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.setParentMenuNode(memuNodeId);
            }
        });
    }

    public void configureMenuNode(MenuNode menuNode) {
        HashMap<String, String> properties = new HashMap<String, String>();
        RealmResults<MenuNodeProperty> RealmProperties = RealmController.getInstance().getPropertiesOfMenuNode(menuNode.getId());
        properties.put("id", String.valueOf(menuNode.getId()));
        properties.put("title", menuNode.getTitle());
        properties.put("image_url", menuNode.getImageUrl());
        for (MenuNodeProperty property : RealmProperties) {
            properties.put(property.getKey(), property.getValue());
        }
        updateProperties(properties);
        configure();
    }

    private Drawable findDrawable(String drawableName) {
        Drawable drawable;
        if ((drawable = ContextCompat.getDrawable(context, context.getResources().getIdentifier(drawableName, "drawable", context.getPackageName()))) != null) {
            return drawable;
        } else {
            try {
                return new IconicsDrawable(context).icon(MaterialDesignIconic.Icon.valueOf(drawableName)).color(Color.RED).sizeDp(24);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(GoogleMaterial.Icon.valueOf(drawableName)).color(Color.RED).sizeDp(24);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(FontAwesome.Icon.valueOf(drawableName)).color(Color.RED).sizeDp(24);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(Octicons.Icon.valueOf(drawableName)).color(Color.RED).sizeDp(24);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(Meteoconcs.Icon.valueOf(drawableName)).color(Color.RED).sizeDp(24);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(CommunityMaterial.Icon.valueOf(drawableName)).color(Color.RED).sizeDp(24);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(WeatherIcons.Icon.valueOf(drawableName)).color(Color.RED).sizeDp(24);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(Typeicons.Icon.valueOf(drawableName)).color(Color.RED).sizeDp(24);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(Entypo.Icon.valueOf(drawableName)).color(Color.RED).sizeDp(24);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(DevIcon.Icon.valueOf(drawableName)).color(Color.RED).sizeDp(24);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(FoundationIcons.Icon.valueOf(drawableName)).color(Color.RED).sizeDp(24);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(Ionicons.Icon.valueOf(drawableName)).color(Color.RED).sizeDp(24);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(Pixeden7Stroke.Icon.valueOf(drawableName)).color(Color.RED).sizeDp(24);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private Typeface findTypeface(String typefaceName) {
        Typeface typeface;
        if ((typeface = Typeface.createFromAsset(context.getAssets(), typefaceName)) != null) {
            return typeface;
        } else {
            try {
                return  (Typeface) EasyFonts.class.getMethod(typefaceName,new Class[] { Context.class }).invoke(null, context);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }



}
