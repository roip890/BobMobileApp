package com.bob.bobmobileapp.tools.UI.views.recyclerview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.bob.bobmobileapp.tools.UI.views.MyBaseView;
import com.bob.bobmobileapp.tools.UI.views.recyclerview.adapters.MyRecyclerViewBaseAdapter;

import java.util.ArrayList;

/**
 * Created by User on 16/12/2017.
 */

public class MyRecyclerView<ViewType extends View> extends MyBaseView {

    protected RecyclerView recyclerView;
    protected MyRecyclerViewBaseAdapter<ViewType> adapter;
    protected int columns, orientation;
    protected GridLayoutManager layoutManager;


    //constructors
    public MyRecyclerView(Context context) {
        this(context, null);
    }

    public MyRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    protected void createMainView() {

        this.view = new RecyclerView(this.getContext());

    }

    @Override
    protected void initMainView() {

        this.columns = 1;
        this.orientation = VERTICAL;
        this.layoutManager = new GridLayoutManager(this.getContext(), this.columns, this.orientation, false);
        this.recyclerView = (RecyclerView) this.view;
        this.adapter = new MyRecyclerViewBaseAdapter<ViewType>();
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setLayoutManager(this.layoutManager);

    }

    public void setLayoutColumns(int columns) {

        this.columns = columns;
        this.layoutManager.setSpanCount(this.columns);

    }


    public void setLayoutOrientation(int orientation) {

        this.orientation = orientation;
        this.layoutManager.setOrientation(this.orientation);

    }

    public void add(View view) {
        try {
            this.adapter.addView((ViewType)view);
        } catch (Exception e) {
            this.recyclerView.addView(view);
        }

    }

    public void add(View view, int index) {

        try {
            this.adapter.addView((ViewType)view, index);
        } catch (Exception e) {
            this.recyclerView.addView(view, index);
        }

    }

    public void addAll(ArrayList<ViewType> views) {
        this.adapter.addAllViews(views);
    }

    public void addAll(ArrayList<ViewType> views, int index) {

        this.adapter.addAllViews(views, index);

    }

    public ViewType getView(int index) {
       return this.adapter.getView(index);
    }

}
