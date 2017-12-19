package com.bob.bobmobileapp.tools.UI.views.recyclerview.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bob.bobmobileapp.tools.UI.views.MyView;

import java.util.ArrayList;

/**
 * Created by User on 17/12/2017.
 */

public class MyRecyclerViewDefaultAdapter extends RecyclerView.Adapter<MyRecyclerViewDefaultAdapter.ViewHolder> {

    private ArrayList<MyView> myViews;

    public MyRecyclerViewDefaultAdapter(ArrayList<MyView> myViews){

        this.myViews = myViews;

    }

    public MyRecyclerViewDefaultAdapter(){

        this(new ArrayList<MyView>());

    }

    @Override
    public MyRecyclerViewDefaultAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        LinearLayout view = new LinearLayout(parent.getContext());
        return new MyRecyclerViewDefaultAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyRecyclerViewDefaultAdapter.ViewHolder holder, int position) {

        holder.setView(myViews.get(position));

    }

    @Override
    public int getItemCount() {

        return myViews.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout linearLayout;
        private View view;

        public ViewHolder(ViewGroup itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView;
        }

        public void setView(View view) {
            this.linearLayout.removeAllViews();
            this.linearLayout.addView(view);
            this.view = view;
        }

        public View getTextView() {
            return this.view;
        }

    }

}
