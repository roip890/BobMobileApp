package com.bob.bobmobileapp.tools.UI.views.recyclerview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioGroup;

import com.bob.bobmobileapp.tools.UI.views.MyBaseView;
import com.bob.bobmobileapp.tools.UI.views.MyView;
import com.bob.bobmobileapp.tools.UI.views.recyclerview.adapters.MyRecyclerViewDefaultAdapter;

import java.util.ArrayList;

/**
 * Created by User on 16/12/2017.
 */

public class MyRecyclerView extends MyBaseView {

    RecyclerView recyclerView;
    ArrayList<MyView> views;
    int columns, orientation;
    GridLayoutManager layoutManager;


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

        this.views = new ArrayList<MyView>();
        this.columns = 1;
        this.orientation = VERTICAL;
        this.layoutManager = new GridLayoutManager(this.getContext(), this.columns, this.orientation, false);
        this.recyclerView = (RecyclerView) this.view;
        this.recyclerView.setAdapter(new MyRecyclerViewDefaultAdapter(this.views));
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

    public void addView(MyView view) {

        this.views.add(view);

    }

    public void addView(int index, MyView view) {

        this.views.add(index, view);

    }

    public void addViews(ArrayList<MyView> views) {

        this.views.addAll(views);

    }

    public void addViews(int index, ArrayList<MyView> views) {

        this.views.addAll(index, views);

    }

    @Override
    public void addView(View view, int index) {
        this.mainContainer.addView(view, index);
    }

    public MyView getView(int index) {

        return this.views.get(index);

    }

}
