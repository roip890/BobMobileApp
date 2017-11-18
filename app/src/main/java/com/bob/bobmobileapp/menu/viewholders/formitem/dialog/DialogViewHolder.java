package com.bob.bobmobileapp.menu.viewholders.formitem.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.StackingBehavior;
import com.afollestad.materialdialogs.Theme;
import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.finals;
import com.bob.bobmobileapp.menu.viewholders.formitem.text.TextViewViewHolder;

import java.util.HashMap;

/**
 * Created by user on 17/09/2017.
 */

public class DialogViewHolder extends TextViewViewHolder {

    protected MaterialDialog.Builder dialogBuilder;
    protected int dialogBackGroundColor, dialogTitleColor, dialogContentColor,
            dialogPositiveColor, dialogNegativeColor, dialogNeutralColor,
            dialogDividerColor, dialogLinkColor, dialogIconMaxSize;
    protected boolean dialogIsPositiveFocus, dialogIsNegativeFocus;
    protected String dialogTitleText, dialogContentText, dialogPositiveText,
            dialogNegativeText, dialogNeutralText;
    protected GravityEnum dialogTitleGravity , dialogContentGravity, dialogButtonsGravity,
            dialogButtonsStackedGravity;
    protected StackingBehavior dialogIsButtonsStacked;
    protected Theme dialogTheme;
    protected Typeface dialogTitleAndButtonsFont, dialogTextFont;
    protected Drawable dialogIcon;

    public DialogViewHolder(Context context, View view) {
        super(context, view, null);
    }

    @Override
    protected void initialize() {
        super.initialize();
        dialogBuilder = new MaterialDialog.Builder(this.context);
        this.dialogBackGroundColor = ContextCompat.getColor(context, R.color.windowBackground);
        this.dialogTitleColor = ContextCompat.getColor(context, R.color.textColorPrimary);
        this.dialogContentColor = ContextCompat.getColor(context, R.color.textColorPrimary);
        this.dialogPositiveColor = ContextCompat.getColor(context, R.color.colorPrimary);
        this.dialogNegativeColor = ContextCompat.getColor(context, R.color.colorPrimary);
        this.dialogNeutralColor = ContextCompat.getColor(context, R.color.colorPrimary);
        this.dialogDividerColor = ContextCompat.getColor(context, R.color.colorPrimary);
        this.dialogLinkColor = ContextCompat.getColor(context, R.color.colorPrimary);
        this.dialogTitleGravity = finals.dialogGravity.get("center");
        this.dialogContentGravity = finals.dialogGravity.get("center");
        this.dialogButtonsGravity = finals.dialogGravity.get("end");
        this.dialogButtonsStackedGravity = finals.dialogGravity.get("center");
        this.dialogIconMaxSize =  (int) context.getResources().getDimension(R.dimen.dialog_default_max_icon_size);
        this.dialogIsPositiveFocus = false;
        this.dialogIsNegativeFocus = true;
        this.dialogIsButtonsStacked = finals.dialogStackingBehavior.get("never");
        this.dialogTitleText = null;
        this.dialogContentText = null;
        this.dialogPositiveText = "Ok";
        this.dialogNegativeText = "Cancle";
        this.dialogNeutralText = null;
        this.dialogTheme = Theme.LIGHT;
        this.dialogTitleAndButtonsFont = null;
        this.dialogTextFont = null;
        this.dialogIcon = null;
    }

    protected void updateProperties(HashMap<String, String> properties) {
        super.updateProperties(properties);
        String curProperty;
        if ((curProperty = properties.get("dialog_background_color")) != null) {
            try {
                this.dialogBackGroundColor = Color.parseColor(curProperty);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("dialog_title_color")) != null) {
            try {
                this.dialogTitleColor = Color.parseColor(curProperty);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("dialog_content_color")) != null) {
            try {
                this.dialogContentColor = Color.parseColor(curProperty);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("dialog_positive_color")) != null) {
            try {
                this.dialogPositiveColor = Color.parseColor(curProperty);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("dialog_negative_color")) != null) {
            try {
                this.dialogNegativeColor = Color.parseColor(curProperty);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("dialog_neutral_color")) != null) {
            try {
                this.dialogNeutralColor = Color.parseColor(curProperty);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("dialog_divider_color")) != null) {
            try {
                this.dialogDividerColor = Color.parseColor(curProperty);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("dialog_link_color")) != null) {
            try {
                this.dialogLinkColor = Color.parseColor(curProperty);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("dialog_title_gravity")) != null) {
            this.dialogTitleGravity = finals.dialogGravity.get(curProperty);
        }
        if ((curProperty = properties.get("dialog_content_gravity")) != null) {
            this.dialogContentGravity = finals.dialogGravity.get(curProperty);
        }
        if ((curProperty = properties.get("dialog_buttons_gravity")) != null) {
            this.dialogButtonsGravity = finals.dialogGravity.get(curProperty);
        }
        if ((curProperty = properties.get("dialog_buttons_stacked_gravity")) != null) {
            this.dialogButtonsStackedGravity = finals.dialogGravity.get(curProperty);
        }
        if ((curProperty = properties.get("dialog_icon_max_size")) != null) {
            try {
                this.dialogIconMaxSize = Integer.parseInt(curProperty);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("dialog_is_positive_focus")) != null) {
            if (curProperty.equals("true")) {
                this.dialogIsPositiveFocus = true;
            } else if (curProperty.equals("false")) {
                this.dialogIsPositiveFocus = false;
            }
        }
        if ((curProperty = properties.get("dialog_is_negative_focus")) != null) {
            if (curProperty.equals("true")) {
                this.dialogIsNegativeFocus = true;
            } else if (curProperty.equals("false")) {
                this.dialogIsNegativeFocus = false;
            }
        }
        if ((curProperty = properties.get("dialog_is_buttons_stacked")) != null) {
            this.dialogIsButtonsStacked = finals.dialogStackingBehavior.get(curProperty);
        }
        if ((curProperty = properties.get("dialog_title_text")) != null) {
            this.dialogTitleText = curProperty;
        }
        if ((curProperty = properties.get("dialog_content_text")) != null) {
            this.dialogContentText = curProperty;
        }
        if ((curProperty = properties.get("dialog_positive_text")) != null) {
            this.dialogPositiveText = curProperty;
        }
        if ((curProperty = properties.get("dialog_negative_text")) != null) {
            this.dialogNegativeText = curProperty;
        }
        if ((curProperty = properties.get("dialog_neutral_text")) != null) {
            this.dialogNeutralText = curProperty;
        }
        if ((curProperty = properties.get("dialog_theme")) != null) {
            if (curProperty.equals("light")) {
                this.dialogTheme = Theme.LIGHT;
            } else if (curProperty.equals("dark")) {
                this.dialogTheme = Theme.DARK;
            }
        }
        if ((curProperty = properties.get("dialog_title_and_buttons_font")) != null) {
            this.dialogTitleAndButtonsFont = this.findTypeface(curProperty);
        }
        if ((curProperty = properties.get("dialog_text_font")) != null) {
            this.dialogTextFont = this.findTypeface(curProperty);
        }
        if ((curProperty = properties.get("dialog_icon")) != null) {
            this.dialogIcon = this.findDrawable(curProperty);
        }
    }

    @Override
    protected void configure() {
        super.configure();
        this.dialogBuilder.backgroundColor(this.dialogBackGroundColor)
                .titleColor(this.dialogTitleColor)
                .contentColor(this.dialogContentColor)
                .positiveColor(this.dialogPositiveColor)
                .negativeColor(this.dialogNegativeColor)
                .neutralColor(this.dialogNeutralColor)
                .dividerColor(this.dialogDividerColor)
                .linkColor(this.dialogLinkColor)
                .titleGravity(this.dialogTitleGravity)
                .contentGravity(this.dialogContentGravity)
                .buttonsGravity(this.dialogButtonsGravity)
                .btnStackedGravity(this.dialogButtonsStackedGravity)
                .maxIconSize(this.dialogIconMaxSize)
                .positiveFocus(this.dialogIsPositiveFocus)
                .negativeFocus(this.dialogIsNegativeFocus)
                .stackingBehavior(this.dialogIsButtonsStacked)
                .title(this.dialogTitleText)
                .content(this.dialogContentText)
                .positiveText(this.dialogPositiveText)
                .negativeText(this.dialogNegativeText)
                .negativeText(this.dialogNeutralText)
                .theme(this.dialogTheme)
                .typeface(dialogTitleAndButtonsFont, dialogTextFont)
                .icon(dialogIcon)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        onPositiveClicked(dialog, which);
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        onNegativeClicked(dialog, which);
                    }
                })
                .onNeutral(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        onNeutralClicked(dialog, which);
                    }
                })
                .customView(getCustomView(), true);

        this.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBuilder.show();
            }
        });
    }

    protected void onPositiveClicked(MaterialDialog dialog, DialogAction which) {
        dialog.dismiss();
    }

    protected void onNegativeClicked(MaterialDialog dialog, DialogAction which) {
        dialog.dismiss();
    }

    protected void onNeutralClicked(MaterialDialog dialog, DialogAction which) {
        dialog.dismiss();
    }

    protected View getCustomView() {
        return null;
    }
}
