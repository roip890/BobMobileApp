package com.bob.bobmobileapp.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.bob.bobmobileapp.BOBApplication;
import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.drawerItems.secondary.CustomCenteredSecondaryDrawerItem;
import com.bob.bobmobileapp.menu.adapters.FormItemsAdapter;
import com.bob.bobmobileapp.menu.adapters.MenuNodesAdapter;
import com.bob.bobmobileapp.realm.RealmController;
import com.bob.bobmobileapp.realm.RealmHelper;
import com.bob.bobmobileapp.realm.objects.FormItem;
import com.bob.bobmobileapp.realm.objects.FormItemProperty;
import com.bob.bobmobileapp.realm.objects.MenuNode;
import com.bob.bobmobileapp.realm.objects.MenuNodeProperty;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private AccountHeader headerResult = null;
    private Drawer result = null;
    private MenuNodesAdapter menuNodesAdapter;
    private FormItemsAdapter formItemsAdapter;
    private RecyclerView recyclerView;
    private long curMenuNodeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);



        //get prefs
        this.getPrefs();

        //db
        this.insertItemToDB();

        //toolbar
        this.initToolbar();

        //nav drawer
        this.buildHeader(false, savedInstanceState);
        this.buildDrawer(savedInstanceState);

        //adapters
        this.setAdapters();

        //recycler view
        this.initRecyclerView();



    }

    //get prefs
    private void getPrefs() {
        if (BOBApplication.get().getInSecureSharedPreferences().contains("cur_menu_node_id")) {
            this.curMenuNodeId = BOBApplication.get().getInSecureSharedPreferences().getLong("cur_menu_node_id", 0);
        } else {
            this.curMenuNodeId = 0;
            BOBApplication.get().getInSecureSharedPreferences().edit().putLong("cur_menu_node_id", this.curMenuNodeId).apply();
        }
    }

    //toolbar
    public void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
    }

    //nav drawer
    private void buildHeader(boolean compact, Bundle savedInstanceState) {
        // Create the AccountHeader
    }

    private void buildDrawer(Bundle savedInstanceState) {
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withIdentifier(1).withName(R.string.drawer_item_home).withIcon(FontAwesome.Icon.faw_home),
                        new PrimaryDrawerItem().withIdentifier(2).withName(R.string.drawer_item_requests).withIcon(FontAwesome.Icon.faw_bell),
                        new PrimaryDrawerItem().withIdentifier(3).withName(R.string.drawer_item_account).withIcon(MaterialDesignIconic.Icon.gmi_account),
                        new SectionDrawerItem(),
                        new SecondaryDrawerItem().withIdentifier(4).withName(R.string.drawer_item_settings).withIcon(MaterialDesignIconic.Icon.gmi_settings),
                        new SecondaryDrawerItem().withIdentifier(5).withName(R.string.drawer_item_privacy_settings).withIcon(FontAwesome.Icon.faw_lock),
                        new SecondaryDrawerItem().withIdentifier(6).withName(R.string.drawer_item_help).withIcon(MaterialDesignIconic.Icon.gmi_help),
                        new SecondaryDrawerItem().withIdentifier(7).withName(R.string.drawer_item_about_us).withIcon(MaterialDesignIconic.Icon.gmi_info),
                        new CustomCenteredSecondaryDrawerItem().withIdentifier(8).withName(R.string.drawer_item_logout).withTextColor(Color.RED)
                ) // add the items we want to use with our Drawer
                .withOnDrawerNavigationListener(new Drawer.OnDrawerNavigationListener() {
                    @Override
                    public boolean onNavigationClickListener(View clickedView) {
                        //this method is only called if the Arrow icon is shown. The hamburger is automatically managed by the MaterialDrawer
                        //if the back arrow is shown. close the activity
                        MainActivity.this.finish();
                        //return true if we have consumed the event
                        return true;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_1:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //menu and form adapter
    private void setAdapters() {
        this.menuNodesAdapter = new MenuNodesAdapter(this, this.curMenuNodeId);
        this.formItemsAdapter = new FormItemsAdapter(this, this.curMenuNodeId);
    }

    //recycler view
    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.setCurMenuNode(this.curMenuNodeId);
    }

    //change menu to form and backward
    public void setCurMenuNode(long curMenuNodeId) {
        MenuNode menuNode = RealmController.get().with(BOBApplication.get()).getMenuNodeById(curMenuNodeId);
        this.curMenuNodeId = (menuNode == null || curMenuNodeId < 0) ? 0 : menuNode.getId();
        BOBApplication.get().getInSecureSharedPreferences().edit().putLong("cur_menu_node_id", this.curMenuNodeId).apply();
        if ((menuNode == null) && (menuNode.isLeaf())) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
            formItemsAdapter.setParentMenuNode(this.curMenuNodeId);
            recyclerView.setAdapter(formItemsAdapter);
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
            menuNodesAdapter.setParentMenuNode(this.curMenuNodeId);
            recyclerView.setAdapter(menuNodesAdapter);
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        //add the values which need to be saved from the accountHeader to the bundle
        outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            //super.onBackPressed();
            MenuNode menuNode = RealmController.get().with(BOBApplication.get()).getMenuNodeById(this.curMenuNodeId);
            if (menuNode != null && menuNode.getParentId() >= 0) {
                setCurMenuNode(menuNode.getParentId());
            }
        }
    }

    private void insertItemToDB() {
        RealmController.with(BOBApplication.get()).insertMenuNode(RealmHelper.get().makeNewMenuNode(0, -1, "main", "https://image.flaticon.com/icons/svg/149/149176.svg"));
        RealmController.with(BOBApplication.get()).insertMenuNode(RealmHelper.get().makeNewMenuNode(1, 0, "food", "http://www.pvhc.net/img8/niexjjzstcseuzdzkvoq.png"));
        RealmController.with(BOBApplication.get()).insertMenuNode(RealmHelper.get().makeNewMenuNode(2, 0, "room service", "https://image.flaticon.com/icons/svg/201/201699.svg"));
        RealmController.with(BOBApplication.get()).insertMenuNode(RealmHelper.get().makeNewMenuNode(3, 1, "room service", "https://image.flaticon.com/icons/svg/201/201699.svg"));
        RealmController.with(BOBApplication.get()).insertMenuNode(RealmHelper.get().makeNewMenuNode(4, 1, "room service", "https://image.flaticon.com/icons/svg/201/201699.svg"));
        RealmController.with(BOBApplication.get()).insertMenuNode(RealmHelper.get().makeNewMenuNode(5, 1, "room service", "https://image.flaticon.com/icons/svg/201/201699.svg"));
        RealmController.with(BOBApplication.get()).insertMenuNodeProperty(RealmHelper.get().makeNewMenuNodeProperty(0, 1, "font_color", "#FFFF0000"));
    }


}
