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

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class UpubInfo extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    public void getInfo() throws SQLException, ClassNotFoundException {
        boolean flag=true;
        Connection conn = null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String dbURL = "jdbc:jtds:sqlserver://apollo.in.cs.ucy.ac.cy:1433";
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        Properties properties = new Properties();
        properties.put("user", "upub");
        properties.put("password", "XuZ3drup" );
        properties.put("databaseName","upub");
        try {
            conn = DriverManager.getConnection(dbURL, properties);
        } catch (SQLException e) {
            flag=false;
            Context context = getApplicationContext();
            CharSequence text = "Cannot connect to DataBase. Info may not be up to date!";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }if(flag){
        String SQL = "Select * From [dbo].[Info]" ;
        System.out.print("done!");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        if(rs.next()) {
            final TextView Address = (TextView) findViewById(R.id.Andress);
            Address.setText(rs.getString(1));
            final TextView OpenHours = (TextView) findViewById(R.id.OpenHours);
            OpenHours.setText(rs.getString(2));
            final TextView phone = (TextView) findViewById(R.id.Telephone);
            phone.setText(rs.getString(3));
            final TextView email = (TextView) findViewById(R.id.Email);
            email.setText(rs.getString(4));
            final TextView services = (TextView) findViewById(R.id.Services);
            services.setText(rs.getString(5));
            final TextView Managers = (TextView) findViewById(R.id.Managers);
            Managers.setText(rs.getString(6));
            final TextView Parking = (TextView) findViewById(R.id.Parking);
            Parking.setText(rs.getString(7));

        }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upub_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
                getInfo();
            }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
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
        getMenuInflater().inflate(R.menu.upub_info, menu);
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
