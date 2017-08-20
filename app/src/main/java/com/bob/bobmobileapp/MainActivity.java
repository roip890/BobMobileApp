package com.bob.bobmobileapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.bob.bobmobileapp.drawerItems.secondary.CustomCenteredSecondaryDrawerItem;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;

public class MainActivity extends AppCompatActivity {

    //save our header or result
    private AccountHeader headerResult = null;
    private Drawer result = null;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Handle Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);


        // Create the AccountHeader
        buildHeader(false, savedInstanceState);

        //Create the drawer
        buildDrawer(savedInstanceState);

        UltimateRecyclerView ultimateRecyclerView = (UltimateRecyclerView) findViewById(R.id.recycler_view);

    }

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

}
