package com.bob.bobmobileapp.tools.UI.views.menu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityOptionsCompat;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.tools.UI.views.MyBaseView;
import com.bob.bobmobileapp.tools.image.ImageActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.hbb20.CountryCodePicker;

import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParser;

/**
 * Created by User on 14/01/2018.
 */

public class MyMenuNodeView extends MyBaseView{

    protected LinearLayout menuNodeViewLayout;
    protected ImageView menuNodeImage;
    protected TextView menuNodeTitle;
    protected String imageUri;

    public MyMenuNodeView(Context context) {
        this(context, null);
    }

    public MyMenuNodeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyMenuNodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.imageUri = null;
    }

    @Override
    protected void createMainView() {
        XmlPullParser linearLayoutParser = getResources().getXml(R.xml.linear_layout_horizontal);
        try {
            linearLayoutParser.next();
            linearLayoutParser.nextTag();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AttributeSet menuNodeViewAttrs = Xml.asAttributeSet(linearLayoutParser);
        this.view = new LinearLayout(this.getContext(), menuNodeViewAttrs);
        this.menuNodeViewLayout = (LinearLayout) this.view;
        this.menuNodeViewLayout.setOrientation(VERTICAL);

        XmlPullParser countryCodePickerParser = getResources().getXml(R.xml.view_default_attribute);
        try {
            countryCodePickerParser.next();
            countryCodePickerParser.nextTag();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AttributeSet menuNodeImageAttrs = Xml.asAttributeSet(countryCodePickerParser);
        this.menuNodeImage = new ImageView(this.getContext(),menuNodeImageAttrs);

        XmlPullParser textViewParser = getResources().getXml(R.xml.view_default_attribute);
        try {
            textViewParser.next();
            textViewParser.nextTag();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AttributeSet menuNodeTitleAttrs = Xml.asAttributeSet(textViewParser);
        this.menuNodeTitle = new TextInputEditText(this.getContext(), menuNodeTitleAttrs);
    }

    @Override
    protected void initMainView() {
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onImageClick();
            }
        });
    }

    public void setImageUri(String uri) {
        this.imageUri = uri;
        Glide
                .with(this.getContext())
                .load(Uri.parse(uri))
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .into(this.imageView);
    }

    public void setImageWidth(int width) {
        this.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        this.imageView.getLayoutParams().width = this.convertPixelsToDp(width);

    }

    public void setImageHeight(int height) {
        this.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        this.imageView.getLayoutParams().height = this.convertPixelsToDp(height);
    }

    protected void onImageClick() {
        if (getContext() instanceof Activity) {
//                    ImageDialog dialog=new ImageDialog(((Activity)getContext()));
//                    dialog.setImageUri(imageUri);
//                    dialog.show();

            if (Build.VERSION.SDK_INT < 21) {
                Toast.makeText(getContext(), "21+ only, keep out", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(getContext(), ImageActivity.class);
                if (imageUri != null) {
                    intent.putExtra("IMAGE_URI", imageUri);
                }
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(((Activity)getContext()), view, "image");
                ((Activity)getContext()).startActivity(intent, options.toBundle());
            }
        }
    }
}
