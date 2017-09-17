package com.bob.bobmobileapp.menu.viewholders.groups;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.finals;
import com.bob.bobmobileapp.realm.RealmController;
import com.bob.bobmobileapp.realm.objects.FormItem;
import com.bob.bobmobileapp.realm.objects.FormItemProperty;
import com.bob.bobmobileapp.tools.Validator;
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
import java.util.ArrayList;
import java.util.HashMap;

import io.realm.RealmResults;

/**
 * Created by user on 17/09/2017.
 */

public class GroupViewHolder extends RecyclerView.ViewHolder {

    protected Validator validator;
    protected Context context;
    protected TextInputLayout textInputLayout;
    protected ArrayList<TextView> textViews;
    protected int fontColor, lineColor, layoutBackgroundColor, errorColor;
    protected float fontSize;
    protected Typeface fontType;
    protected String textLable, inputType, hint, errorLable, emptyErrorLable, gravity;
    protected Drawable startDrawable, layoutBackground;
    protected boolean boldText, underlineText, italicText, notEmpty;

    public GroupViewHolder(Context context, View view, Validator validator) {
        super(view);
        this.context = context;
        this.validator = validator;
        initView(view);
        initialize();
        configure();
    }

    public GroupViewHolder(Context context, View view) {
        this(context, view, null);
    }

    public TextInputLayout getTextInputLayout() {
        return textInputLayout;
    }

    public void setTextInputLayout(TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;
    }

    public ArrayList<TextView> getTextViews() {
        return textViews;
    }

    public void setTextViews(ArrayList<TextView> textViews) {
        this.textViews = textViews;
    }

    protected void initView(View view) {
        setTextInputLayout((TextInputLayout) view.findViewById(R.id.text_input_layout));
        setTextView((TextInputEditText) view.findViewById(R.id.edit_text));
    }

    protected void initialize() {
        this.inputType = "none";
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

    }

    protected void updateProperties(HashMap<String, String> properties) {
        String curProperty;
        if ((curProperty = properties.get("input_type")) != null) {
            try {
                this.inputType = curProperty;
            } catch (IllegalArgumentException e) {
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
        if ((curProperty = properties.get("text_lable")) != null) {
            this.textLable = curProperty;
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
            this.gravity = curProperty;
        }
    }

    protected void configure() {

        startDrawable.setColorFilter(new PorterDuffColorFilter(this.fontColor, PorterDuff.Mode.SRC_IN));
        this.textView.setCompoundDrawablesRelativeWithIntrinsicBounds(startDrawable, null, null, null);

        this.textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if ((motionEvent.getAction() == MotionEvent.ACTION_UP) && !textView.getText().equals("")
                        && isOnResetDrawable(motionEvent.getX()) && textView.getCompoundDrawablesRelative()[0] != null) {
                    textView.setText("");
                }
                return true;
            }
        });

        this.textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
                if (!text.equals("")) {
                    startDrawable.setColorFilter(new PorterDuffColorFilter(fontColor, PorterDuff.Mode.SRC_IN));
                    textView.setCompoundDrawablesRelativeWithIntrinsicBounds(startDrawable, null, null, null);
                }
            }

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                SpannableString span = new SpannableString(text.toString());
                if(underlineText)
                {
                    span.setSpan(new UnderlineSpan(),0, text.length() - 1 , 0);
                }
                if(boldText)
                {
                    span.setSpan(new StyleSpan(Typeface.BOLD), 0, text.length() - 1 ,  0);
                }
                if(italicText)
                {
                    span.setSpan(new StyleSpan(Typeface.ITALIC), 0, text.length() - 1 , 0);
                }
                textView.setText(span, TextView.BufferType.SPANNABLE);
                validateTextField(text.toString());
            }

            @Override
            public void afterTextChanged(Editable text) {
                validateTextField(text.toString());
            }
        });

        this.textView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validateTextField(((TextView) v).getText().toString());
                }
            }
        });

        Integer inputType = finals.inputTypes.get(this.inputType);
        if (gravity != null) {
            this.textView.setInputType(inputType);
        }
        this.textView.setTextColor(this.fontColor);
        this.textView.getBackground().setColorFilter(this.lineColor, PorterDuff.Mode.SRC_ATOP);
        if (textInputLayout != null) {
            this.textInputLayout.setBackgroundColor(this.layoutBackgroundColor);
            this.textInputLayout.setBackground(this.layoutBackground);
        }
        this.setErrorTextColor(this.errorColor);
        this.textView.setTextSize(this.fontSize);
        this.textView.setTypeface(this.fontType);
        this.textView.setHint(this.hint);
        if (this.italicText && this.boldText) {
            this.textView.setTypeface(this.textView.getTypeface(), Typeface.BOLD_ITALIC);
        } else {
            if (this.boldText) {
                this.textView.setTypeface(this.textView.getTypeface(), Typeface.BOLD);
            }
            if (this.italicText) {
                this.textView.setTypeface(this.textView.getTypeface(), Typeface.ITALIC);
            }
        }
        if (this.underlineText) {
            this.textView.setPaintFlags(this.textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        }
        Integer gravity = finals.gravity.get(this.gravity);
        if (gravity != null) {
            this.textView.setGravity(gravity);
        }
        this.setValue();
    }

    public void configureFormItem(FormItem formItem) {
        HashMap<String, String> properties = new HashMap<String, String>();
        RealmResults<FormItemProperty> RealmProperties = RealmController.getInstance().getPropertiesOfFormItem(formItem.getId());
        for (FormItemProperty property : RealmProperties) {
            properties.put(property.getKey(), property.getValue());
        }
        updateProperties(properties);
        configure();
    }

    protected void setValue() {
        this.textView.setText(this.textLable);
    }
    private void validateTextField(String text) {
        if (textInputLayout != null) {
            if (!this.isValid(text)) {
                if (text.equals("")) {
                    startDrawable.setColorFilter(new PorterDuffColorFilter(this.fontColor, PorterDuff.Mode.SRC_IN));
                    this.textView.setCompoundDrawablesRelativeWithIntrinsicBounds(startDrawable, null, null, null);
                    if (notEmpty) {
                        textInputLayout.setError(emptyErrorLable);
                    } else {
                        textInputLayout.setError(errorLable);
                    }
                } else {
                    Drawable resetDrawable = findDrawable("gmi_close_circle");
                    resetDrawable.setColorFilter(new PorterDuffColorFilter(this.errorColor, PorterDuff.Mode.SRC_IN));
                    startDrawable.setColorFilter(new PorterDuffColorFilter(this.errorColor, PorterDuff.Mode.SRC_IN));
                    this.textView.setCompoundDrawablesRelativeWithIntrinsicBounds(startDrawable, null, resetDrawable, null);
                    textInputLayout.setError(errorLable);
                }
            } else if (!text.equals("")) {
                Drawable resetDrawable = findDrawable("gmi_close_circle");
                resetDrawable.setColorFilter(new PorterDuffColorFilter(this.fontColor, PorterDuff.Mode.SRC_IN));
                startDrawable.setColorFilter(new PorterDuffColorFilter(this.fontColor, PorterDuff.Mode.SRC_IN));
                this.textView.setCompoundDrawablesRelativeWithIntrinsicBounds(startDrawable, null, resetDrawable, null);
                textInputLayout.setError(null);
            } else {
                startDrawable.setColorFilter(new PorterDuffColorFilter(this.fontColor, PorterDuff.Mode.SRC_IN));
                this.textView.setCompoundDrawablesRelativeWithIntrinsicBounds(startDrawable, null, null, null);
                if (notEmpty) {
                    textInputLayout.setError(emptyErrorLable);
                }
            }
        }
    }

    private void setErrorTextColor(int color) {
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

    private boolean isValid(String text) {
        if (this.validator != null) {
            return this.validator.isValid(text);
        }
        return true;
    }

    //extern this functions
    public Drawable findDrawable(String drawableName) {
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

    public Typeface findTypeface(String typefaceName) {
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

    private boolean isOnResetDrawable(float x) {
        if (context.getResources().getBoolean(R.bool.is_right_to_left)) {
            if(x >= this.textView.getTotalPaddingRight()) {
                return true;
            }
        } else {
            if(x <= this.textView.getTotalPaddingLeft()) {
                return true;
            }
        }
        return false;
    }
}
