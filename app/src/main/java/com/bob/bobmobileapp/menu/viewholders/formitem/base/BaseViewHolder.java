package com.bob.bobmobileapp.menu.viewholders.formitem.base;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bob.bobmobileapp.BOBApplication;
import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.finals;
import com.bob.bobmobileapp.realm.RealmController;
import com.bob.bobmobileapp.realm.objects.FormItem;
import com.bob.bobmobileapp.realm.objects.FormItemProperty;
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
    protected TextInputLayout textInputLayout;
    protected int fontColor, lineColor, layoutBackgroundColor, errorColor, gravity;
    protected float fontSize;
    protected Typeface fontType;
    protected String hint, errorLable, emptyErrorLable;
    protected Drawable startDrawable, layoutBackground;
    protected boolean boldText, underlineText, italicText, notEmpty;

    public BaseViewHolder(Context context, View view, Validator validator) {
        super(view);
        this.context = context;
        initView(view);
        initialize();
        configure();
    }

    public BaseViewHolder(Context context, View view) {
        this(context, view, null);
    }

    public TextInputLayout getTextInputLayout() {
        return textInputLayout;
    }

    public void setTextInputLayout(TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;
    }

    protected abstract void initView(View view);

    protected void initialize() {
        this.layoutBackgroundColor = ContextCompat.getColor(context, R.color.windowBackground);
        this.fontColor = ContextCompat.getColor(context, R.color.textColorPrimary);
        this.lineColor = ContextCompat.getColor(context, R.color.colorPrimary);
        this.errorColor = ContextCompat.getColor(context, R.color.colorError);
        this.fontSize = context.getResources().getDimension(R.dimen.text_size_medium);
        this.startDrawable = null;
        this.layoutBackground = null;
        this.fontType = null;
        this.errorLable = "Please enter valid input!";
        this.emptyErrorLable = "Please enter an input!";
        this.boldText = false;
        this.underlineText = false;
        this.italicText = false;
        this.notEmpty = false;
        this.hint = null;
        this.gravity = finals.gravity.get("start");
    }

    protected void updateProperties(HashMap<String, String> properties) {
        String curProperty;
        if ((curProperty = properties.get("font_color")) != null) {
            try {
                this.fontColor = Color.parseColor(curProperty);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("line_color")) != null) {
            try {
                this.lineColor = Color.parseColor(curProperty);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("foreground_color")) != null) {
            try {
                this.fontColor = Color.parseColor(curProperty);
                this.lineColor = Color.parseColor(curProperty);
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
        if ((curProperty = properties.get("error_color")) != null) {
            try {
                this.errorColor = Color.parseColor(curProperty);
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
        if ((curProperty = properties.get("start_drawable")) != null) {
            this.startDrawable = this.findDrawable(curProperty);
        }
        if ((curProperty = properties.get("layout_background")) != null) {
            this.layoutBackground = ContextCompat.getDrawable(context, context.getResources().getIdentifier(curProperty, "drawable", context.getPackageName()));
        }
        if ((curProperty = properties.get("font_type")) != null) {
            this.fontType = this.findTypeface(curProperty);
        }
        if ((curProperty = properties.get("error_lable")) != null) {
            this.errorLable = curProperty;
        }
        if ((curProperty = properties.get("empty_error_lable")) != null) {
            this.emptyErrorLable = curProperty;
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
        if ((curProperty = properties.get("not_empty")) != null) {
            if (curProperty.equals("true")) {
                this.notEmpty = true;
            } else if (curProperty.equals("false")) {
                this.notEmpty = false;
            }
        }
        if ((curProperty = properties.get("hint")) != null) {
            this.errorLable = curProperty;
        }
        if ((curProperty = properties.get("gravity")) != null) {
            this.gravity = finals.gravity.get(curProperty);
        }
    }

    protected abstract void configure();

    public void configureFormItem(FormItem formItem) {
        HashMap<String, String> properties = new HashMap<String, String>();
        RealmResults<FormItemProperty> RealmProperties = RealmController.get().with(BOBApplication.get()).getPropertiesOfFormItem(formItem.getId());
        for (FormItemProperty property : RealmProperties) {
            properties.put(property.getKey(), property.getValue());
        }
        updateProperties(properties);
        configure();
    }

    protected void setErrorTextColor(int color) {
        if (textInputLayout != null) {
            try {
                Field fErrorView = TextInputLayout.class.getDeclaredField("mErrorView");
                fErrorView.setAccessible(true);
                TextView mErrorView = (TextView) fErrorView.get(textInputLayout);
                Field fCurTextColor = TextView.class.getDeclaredField("mCurTextColor");
                fCurTextColor.setAccessible(true);
                fCurTextColor.set(mErrorView, color);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //extern this functions
    protected Drawable findDrawable(String drawableName) {
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
