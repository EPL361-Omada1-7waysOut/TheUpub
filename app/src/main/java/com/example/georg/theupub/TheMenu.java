package com.example.georg.theupub;
/*
*Copyright (c) <2015> <7WaysOut>

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:



The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.



THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
* */

import android.content.Intent;
import android.content.res.Resources;
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
import android.widget.ListView;

public class TheMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView list;
    String[] Titles;
    String[] Descriptions;

    //List of images to be put in the listt
    int [] images={
            R.drawable.shisha,
            R.drawable.keo,
            R.drawable.cas,
            R.drawable.frappe,
            R.drawable.icedlatte,
            R.drawable.icetea,
            R.drawable.lem,
            R.drawable.lembub,
            R.drawable.coke,
            R.drawable.orange,
            R.drawable.carrot,
            R.drawable.apple,
            R.drawable.milkshake,
            R.drawable.smoothie,
            R.drawable.espr,
            R.drawable.dubespr,
            R.drawable.cyprus,
            R.drawable.cap,
            R.drawable.hotchoc,
            R.drawable.inst,
            R.drawable.latte,
            R.drawable.mocha,
            R.drawable.filter,


 };


 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        list=(ListView) findViewById(R.id.listView);
        Resources res=getResources();
        Titles=res.getStringArray(R.array.MenuTitle);
        Descriptions=res.getStringArray(R.array.MenuDescription);

        MyAdapter adapter=new MyAdapter(this,Titles,images,Descriptions);
        list.setAdapter(adapter);
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
        getMenuInflater().inflate(R.menu.the_menu, menu);
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
        } else if (id == R.id.nav_Profile) {//to profile activity
            if(LoginActivity.getISadmin()) {
                startAdmin();
            }
            else {
                startProfile();
            }
        } else if (id == R.id.nav_Logout) {//to Logout activity
            startLogout();
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

    public void startAdmin(){//to Admin activity
        Intent intent=new Intent(this,AdministratorActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void startLogout(){//to Login activity
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
        this.finish();
    }

}
