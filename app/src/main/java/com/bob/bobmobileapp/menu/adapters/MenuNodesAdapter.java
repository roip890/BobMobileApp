package com.bob.bobmobileapp.menu.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.menu.viewholders.menunode.MenuNodeViewHolder;
import com.bob.bobmobileapp.realm.RealmController;
import com.bob.bobmobileapp.realm.objects.MenuNode;

import java.util.List;

/**
 * Created by user on 17/09/2017.
 */

public class MenuNodesAdapter extends RecyclerView.Adapter<MenuNodeViewHolder> {

    private long parentMenuNodeId;
    private List<MenuNode> menuNodes;
    private Context context;

    public MenuNodesAdapter(Context context) {
        this(context, -1);
    }

    public MenuNodesAdapter(Context context, long parentMenuNodeId) {
        this.parentMenuNodeId = parentMenuNodeId;
        this.menuNodes = RealmController.getInstance().getSubMenuNodes(this.parentMenuNodeId);
        this.context = context;
    }

    @Override
    public MenuNodeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_node_item, null);
        MenuNodeViewHolder menuNodeViewHolder = new MenuNodeViewHolder(this.context, this, layoutView);
        return menuNodeViewHolder;
    }

    @Override
    public void onBindViewHolder(MenuNodeViewHolder menuNodeViewHolder, int position) {
        menuNodeViewHolder.configureMenuNode(menuNodes.get(position));
        menuNodeViewHolder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return this.menuNodes.size();
    }

    public void setParentMenuNode(long menuNodeId) {
        this.menuNodes.clear();
        this.menuNodes.addAll(RealmController.getInstance().getSubMenuNodes(menuNodeId));
        this.notifyDataSetChanged();
    }
}
