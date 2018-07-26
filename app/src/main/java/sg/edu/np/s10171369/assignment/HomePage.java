package sg.edu.np.s10171369.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class HomePage extends AppCompatActivity {

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView nDrawer;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // Setup drawer view
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        //ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        //this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawer.addDrawerListener(toggle);
        //toggle.syncState();

        nDrawer = findViewById(R.id.nav_view);
        setupDrawerContent(nDrawer);

        Fragment fragment = new HomeFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.flContent, fragment);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;
        int id = item.getItemId();

        /*if (id == R.id.nav_home){
            Snackbar snackBar = Snackbar.make(findViewById(R.id.drawer_layout),"Coming Soon", Snackbar.LENGTH_SHORT);
            snackBar.show();
        }
        else if (id == R.id.nav_guides) {
            Intent intent = new Intent(this, Guides.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_tips) {
            Snackbar snackBar = Snackbar.make(findViewById(R.id.drawer_layout),"Coming Soon", Snackbar.LENGTH_SHORT);
            snackBar.show();
        }
        else if (id == R.id.nav_extrainfo) {
            Snackbar snackBar = Snackbar.make(findViewById(R.id.drawer_layout),"Coming Soon", Snackbar.LENGTH_SHORT);
            snackBar.show();
        }
        else if (id == R.id.nav_credits) {
            Snackbar snackBar = Snackbar.make(findViewById(R.id.drawer_layout),"Coming Soon", Snackbar.LENGTH_SHORT);
            snackBar.show();
        }*/

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        Snackbar snackBar = null;
        switch(id) {
            case R.id.nav_home:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragment = new HomeFragment();
                break;
            case R.id.nav_guides:
                fragment = new Guides();
                break;
            case R.id.nav_tips:
                snackBar = Snackbar.make(findViewById(R.id.linearLayout),"Coming Soon", Snackbar.LENGTH_SHORT);
                break;
            case R.id.nav_extrainfo:
                break;
            case R.id.nav_credits:
                break;
        }

        /*try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        //FragmentManager fragmentManager = getSupportFragmentManager();
        //fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        //item.setChecked(true);

        //setTitle(item.getTitle());

        //drawer.closeDrawers();
        FragmentTransaction ft = null;
        try {
             ft = getSupportFragmentManager().beginTransaction();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (snackBar != null) {
            snackBar.show();
        }
        else {
            ft.replace(R.id.flContent, fragment);
            ft.commit();
        }
    }
}
