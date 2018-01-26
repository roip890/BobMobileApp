package com.bob.bobmobileapp.menu.viewholders.formitem.base;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.bob.bobmobileapp.BOBApplication;
import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.finals;
import com.bob.bobmobileapp.realm.RealmController;
import com.bob.bobmobileapp.realm.objects.FormItem;
import com.bob.bobmobileapp.realm.objects.FormItemProperty;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyTextView;
import com.bob.bobmobileapp.tools.validators.Validator;
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
import com.vstechlab.easyfonts.EasyFonts;

import java.lang.reflect.Field;
import java.util.HashMap;

import io.realm.RealmResults;

/**
 * Created by user on 17/09/2017.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    protected Context context;

    public BaseViewHolder(Context context, View view, Validator validator) {
        super(view);
        this.context = context;
        initView(view);
        initialize();
    }

    public BaseViewHolder(Context context, View view) {
        this(context, view, null);
    }

    protected abstract void initView(View view);

    abstract protected void initialize();

    abstract protected void updateProperties(HashMap<String, String> properties);

    public void configureFormItem(FormItem formItem) {
        HashMap<String, String> properties = new HashMap<String, String>();
        RealmResults<FormItemProperty> RealmProperties = RealmController.get().with(BOBApplication.get()).getPropertiesOfFormItem(formItem.getId());
        for (FormItemProperty property : RealmProperties) {
            properties.put(property.getKey(), property.getValue());
        }
        updateProperties(properties);
    }

    //extern this functions
    protected int convertPixelsToDp(int px) {
        return px * ((int)(this.context.getResources().getDisplayMetrics().density));
    }

    protected int convertPixelsToSp(int px) {
        return convertDpToSp(convertPixelsToDp(px));
    }

    protected int convertDpToPixels(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, this.context.getResources().getDisplayMetrics());
    }

    protected int convertSpToPixels(float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, this.context.getResources().getDisplayMetrics());
    }

    protected int convertDpToSp(float dp) {
        return (int) (convertDpToPixels(dp) / (float) convertSpToPixels(dp));
    }

    protected int convertSpToDp(float sp) {
        return convertPixelsToDp(convertSpToPixels(sp));
    }

    protected Drawable findDrawable(String drawableName) {
        Drawable drawable;
        if ((drawable = ContextCompat.getDrawable(context, context.getResources().getIdentifier(drawableName, "drawable", context.getPackageName()))) != null) {
            return drawable;
        } else {
            try {
                return new IconicsDrawable(context).icon(MaterialDesignIconic.Icon.valueOf(drawableName));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(GoogleMaterial.Icon.valueOf(drawableName));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(FontAwesome.Icon.valueOf(drawableName));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(Octicons.Icon.valueOf(drawableName));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(Meteoconcs.Icon.valueOf(drawableName));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(CommunityMaterial.Icon.valueOf(drawableName));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(WeatherIcons.Icon.valueOf(drawableName));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(Typeicons.Icon.valueOf(drawableName));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(Entypo.Icon.valueOf(drawableName));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(DevIcon.Icon.valueOf(drawableName));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(FoundationIcons.Icon.valueOf(drawableName));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(Ionicons.Icon.valueOf(drawableName));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                return new IconicsDrawable(context).icon(Pixeden7Stroke.Icon.valueOf(drawableName));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    protected Typeface findTypeface(String typefaceName) {
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
