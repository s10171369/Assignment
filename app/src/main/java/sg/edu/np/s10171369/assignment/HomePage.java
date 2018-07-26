package sg.edu.np.s10171369.assignment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
        toolbar = findViewById(R.id.titleToolBar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        nDrawer = findViewById(R.id.nav_view);
        setupDrawerContent(nDrawer);

        Fragment fragment = new HomeFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        setTitle("Home");
        ft.add(R.id.flContent, fragment);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        FragmentManager fm = getSupportFragmentManager();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if (fm.getBackStackEntryCount() > 1){
            fm.popBackStack();
        }
        else if (fm.getBackStackEntryCount() == 1){
            fm.popBackStack();
            setTitle("Home");
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // The action bar home/up action should open or close the drawer.
        switch (id) {
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

        Fragment fragment = null;
        int id = item.getItemId();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        Snackbar snackBar = null;
        switch(id) {
            case R.id.nav_home:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragment = new HomeFragment();
                setTitle("Home");
                break;
            case R.id.nav_guides:
                fragment = new Guides();
                setTitle("Guides");
                break;
            case R.id.nav_tips:
                snackBar = Snackbar.make(findViewById(R.id.linearLayout),"Coming Soon", Snackbar.LENGTH_SHORT);
                break;
            case R.id.nav_extrainfo:
                break;
            case R.id.nav_credits:
                break;
        }

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
            ft.addToBackStack(null);
            ft.commit();
        }
    }
}
