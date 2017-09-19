package com.bob.bobmobileapp;

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

import com.bob.bobmobileapp.drawerItems.secondary.CustomCenteredSecondaryDrawerItem;
import com.bob.bobmobileapp.menu.adapters.FormItemsAdapter;
import com.bob.bobmobileapp.realm.RealmController;
import com.bob.bobmobileapp.realm.objects.FormItem;
import com.bob.bobmobileapp.realm.objects.FormItemProperty;
import com.bob.bobmobileapp.realm.objects.MenuNode;
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
    private RecyclerView recyclerView;
    private long curMenuNodeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get prefs
        this.getPrefs();

        //toolbar
        this.initToolbar();

        //nav drawer
        this.buildHeader(false, savedInstanceState);
        this.buildDrawer(savedInstanceState);

        //recycler view




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

    //recycler view
    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        if (curMenuNodeId != -1) {
            recyclerView.setAdapter(new FormItemsAdapter(this,));
        }

    }

    //get prefs
    private void getPrefs() {
        if (BOBApplication.get().getInSecureSharedPreferences().contains("cur_menu_node_id")) {
            this.curMenuNodeId = BOBApplication.get().getInSecureSharedPreferences().getLong("cur_menu_node_id", -1);
        } else {
            this.curMenuNodeId = -1;
            BOBApplication.get().getInSecureSharedPreferences().edit().putLong("cur_menu_node_id", this.curMenuNodeId);
        }
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

            super.onBackPressed();
        }
    }

    private void insertItemToDB() {
        RealmController.getInstance().insertMenuNode(makeNewMenuNode(0, -1, "main", null));
        RealmController.getInstance().insertMenuNode(makeNewMenuNode(0, -1, "main", null));
        RealmController.getInstance().insertMenuNode(makeNewMenuNode(0, -1, "main", null));
    }

    private MenuNode makeNewMenuNode(long id, long parentId, String title, String imageUrl) {
        MenuNode menuNode = new MenuNode();
        menuNode.setId(id);
        menuNode .setParentId(parentId);
        menuNode.setTitle(title);
        menuNode.setTitle(imageUrl);
        return menuNode;
    }

    private FormItem makeNewFormItem(long id, long parentId, String type) {
        FormItem formItem = new FormItem();
        formItem.setId(id);
        formItem.setParentId(parentId);
        formItem.setType(type);
        return formItem;
    }

    private FormItemProperty makeNewFormItemProperty(long id, long parentId, String key, String value) {
        FormItemProperty formItemProperty = new FormItemProperty();
        formItemProperty.setId(id);
        formItemProperty.setParentId(parentId);
        formItemProperty.setKey(key);
        formItemProperty.setValue(value);
        return formItemProperty;
    }

}
