package com.example.georg.theupub;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class AdministratorActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.administrator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Navigation menu
        int id = item.getItemId();

        if (id == R.id.nav_Info) {//to info activity
            startInfo();
        } else if (id == R.id.nav_Menu) {//to menu activity
            startMenu();
        } else if (id == R.id.nav_Offers) {//to offers activity
            startOffers();
        } else if (id == R.id.nav_Events) {//to events activity
            startEvents();
        } else if (id == R.id.nav_Profile) {//to events activity
            startProfile();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void startInfo(){//starting Activity Info
        Intent intent=new Intent(this,UpubInfo.class);
        startActivity(intent);
        this.finish();
    }

    public void startMenu(){//to menu activity
        Intent intent=new Intent(this,TheMenu.class);
        startActivity(intent);
        this.finish();
    }
    public void startOffers(){//to offers activity
        Intent intent=new Intent(this,Offers.class);
        startActivity(intent);
        this.finish();
    }

    public void startEvents(){//to events activity
        Intent intent=new Intent(this,Events.class);
        startActivity(intent);
        this.finish();
    }

    public void startProfile(){//to Profile activity
        Intent intent=new Intent(this,ProfileActivity.class);
        startActivity(intent);
        this.finish();
    }
}
