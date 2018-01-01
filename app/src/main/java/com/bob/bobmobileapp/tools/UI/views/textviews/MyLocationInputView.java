package com.bob.bobmobileapp.tools.UI.views.textviews;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.map.MapsActivity;
import com.bob.bobmobileapp.tools.style.Icons;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment;
import com.mikepenz.iconics.IconicsDrawable;

import org.xmlpull.v1.XmlPullParser;

/**
 * Created by User on 24/12/2017.
 */

public class MyLocationInputView extends MyLocationOutputView {

    public MyLocationInputView(Context context) {
        this(context, null);
    }

    public MyLocationInputView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyLocationInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initMainView() {
        super.initMainView();

        this.setMapDrawableColor(ContextCompat.getColor(this.getContext(), R.color.textColorPrimary));
        this.setMapDrawable(((IconicsDrawable) Icons.get().findDrawable(this.getContext(), "gmd_my_location")).sizeDp(24));
        this.setMapDrawableEnable(true);

        this.mapIconImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getContext() instanceof FragmentActivity) {


                    PlaceAutocompleteFragment autocompleteFragment = new PlaceAutocompleteFragment();

                    autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
                        @Override
                        public void onPlaceSelected(Place place) {
                            // TODO: Get info about the selected place.
                            Log.i("Select", "Place: " + place.getName());//get place details here
                        }

                        @Override
                        public void onError(Status status) {
                            // TODO: Handle the error.
                            Log.i("Error", "An error occurred: " + status);
                        }
                    });

                    FragmentManager fm = ((FragmentActivity)getContext()).getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.place_autocomplete_fragment, autocompleteFragment);
                    ft.commit();


                }

//                Intent intent = new Intent(getContext(), MapsActivity.class);
//                getContext().startActivity(intent);

            }
        });

//        this.setBackgroundColor(Color.RED);
//        this.mapIconImageView.setColorFilter(Color.GREEN);
//        this.textView.setBackgroundColor(Color.YELLOW);

    }

}

