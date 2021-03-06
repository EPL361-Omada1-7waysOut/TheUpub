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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class AdministratorActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button mButton;
    private TextView outScan;
    private EditText addPointsText;
    private EditText remPointsText;
    private Button addPointsButton;
    private Button remPointsButton;
    private  String re;
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

        //Find Components
        mButton = (Button) findViewById(R.id.qrButton);
        addPointsText =(EditText) findViewById(R.id.typeAddPoints);
        remPointsText =(EditText) findViewById(R.id.typeRemPoints);
        addPointsButton = (Button) findViewById(R.id.addPointsButton);
        remPointsButton = (Button) findViewById(R.id.remPointsButton);

        // Scanner Scanner Scanner
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(AdministratorActivity.this);
                integrator.initiateScan();
            }
        });

        //Handle Text Click
        addPointsText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                editTextClick(addPointsText);
            }
        });

        remPointsText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                editTextClick(remPointsText);
            }
        });

        // Adding Points
        addPointsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               addPoints(addPointsText);
            }
        });

        // Removing Points
        remPointsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removePoints(remPointsText);
            }
        });

    }
    public boolean addPointss(int numpoints){
        int currentpoints = 0;
        Connection conn = null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String dbURL = "jdbc:jtds:sqlserver://apollo.in.cs.ucy.ac.cy:1433";
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
        } catch (ClassNotFoundException e) {
          return false;
        }
        Properties properties = new Properties();
        properties.put("user", "upub");
        properties.put("password", "XuZ3drup" );
        properties.put("databaseName","upub");
        try {
            conn = DriverManager.getConnection(dbURL, properties);
        } catch (SQLException e) {
            return false;
        }
        String SQL = "Select * From [dbo].[User] where ID='"+re+"'" ;
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            return false;
        }
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(SQL);
        } catch (SQLException e) {
            return false;
        }
        try {
            if(rs.next()){
                System.out.print(rs.getInt(7));
                currentpoints=rs.getInt(7);
            }
        } catch (SQLException e) {
            return false;
        }
        String pro="EXEC dbo.addpoints ?,?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(pro);
            ps.setEscapeProcessing(true);
            ps.setInt(1, Integer.parseInt(re));
            ps.setInt(2,currentpoints+numpoints);
            ps.execute();
        } catch (SQLException e) {
            return false;
        }
    return true;

    }
    public boolean RemovePointss(int numpoints){

        int currentpoints = 0;
        Connection conn = null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String dbURL = "jdbc:jtds:sqlserver://apollo.in.cs.ucy.ac.cy:1433";
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            return false;
        }
        Properties properties = new Properties();
        properties.put("user", "upub");
        properties.put("password", "XuZ3drup" );
        properties.put("databaseName","upub");
        try {
            conn = DriverManager.getConnection(dbURL, properties);
        } catch (SQLException e) {
         return false;
        }
        String SQL = "Select * From [dbo].[User] where ID='"+re+"'" ;
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
          return false;
        }
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(SQL);
        } catch (SQLException e) {
            return false;
        }
        try {
            if(rs.next()){
                System.out.print(rs.getInt(7));
                currentpoints=rs.getInt(7);
            }
        } catch (SQLException e) {
            return false;
        }
        String pro="EXEC dbo.addpoints ?,?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(pro);
            ps.setEscapeProcessing(true);
            ps.setInt(1, Integer.parseInt(re));
            ps.setInt(2,currentpoints-numpoints);
            ps.execute();
        } catch (SQLException e) {
            return false;
        }

    return true;
    }
    public boolean checkPointsString(String s){
        for(int i=0;i<s.length();i++){
            if((s.charAt(i)!='0')&&
            (s.charAt(i)!='1')&&
            (s.charAt(i)!='2')&&
            (s.charAt(i)!='3')&&
            (s.charAt(i)!='4')&&
            (s.charAt(i)!='5')&&
            (s.charAt(i)!='6')&&
            (s.charAt(i)!='7')&&
            (s.charAt(i)!='8')&&
            (s.charAt(i)!='9')
            )return false;
        }
        return true;
    }
    public void addPoints(EditText T){
        String points = T.getText().toString();
        if(!checkPointsString(points)){
            Context context = getApplicationContext();
            CharSequence text = "Points should be characters from 0-9";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
        int numPoints=0;
        if(points.equals("")||points.equals("type points")){
            numPoints=0;
        }else{
            //This will change after the database
            numPoints = Integer.parseInt(points);
            if(addPointss(numPoints)) {
                ProfileActivity.userPoints = numPoints + ProfileActivity.userPoints;
                T.setText("");
                Context context = getApplicationContext();
                CharSequence text = "Points Added";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            else{
                Context context = getApplicationContext();
                CharSequence text = "Can't add points. No connection to the DataBase!";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
    }

    public void removePoints(EditText T){

        String points = T.getText().toString();
        if(!checkPointsString(points)){
            Context context = getApplicationContext();
            CharSequence text = "Points should be characters from 0-9";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
        int numPoints=0;
        if(points.equals("")||points.equals("type points")){
            numPoints=0;
        }else{
            //This will change after the database
            numPoints = Integer.parseInt(points);
            if(RemovePointss(numPoints)){
            ProfileActivity.userPoints=ProfileActivity.userPoints-numPoints;
            T.setText("");
            Context context = getApplicationContext();
            CharSequence text = "Points Removed";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();}
            else{
                Context context = getApplicationContext();
                CharSequence text = "Can't remove points. No connection to the DataBase!";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
    }

    public void editTextClick(EditText T){
        T.setText("");
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {
             re = scanResult.getContents();
            outScan = (TextView) findViewById(R.id.scanOut);
            outScan.setText(re);
        }
        // else continue with any other code you need in the method

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
