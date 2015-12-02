package com.example.georg.theupub;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String sample_user = "1234";
    public static  int userPoints=0;
    private TextView pointText;

    /*
    * This method generates a Bitmap image from a
    * BitMatrix.
    * */


    public static Bitmap toBitmap(BitMatrix matrix){
        int height = matrix.getHeight();
        int width = matrix.getWidth();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                bmp.setPixel(x, y, matrix.get(x,y) ? Color.BLACK : Color.WHITE);
            }
        }
        return bmp;
    }
    public boolean getPoints() {
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
        String SQL = "Select * From [dbo].[User] where ID='"+sample_user+"'" ;
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
                userPoints=rs.getInt(7);
            }
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

            if(!getPoints()) {
                Context context = getApplicationContext();
                CharSequence text = "Can't get points. Can't reach database!";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        QRCodeWriter writer = new QRCodeWriter();

        try {
            // BitMatrix matrix = writer.encode(
            //  "Giorgos Demosthenous 953157", BarcodeFormat.QR_CODE, 400, 400);
            BitMatrix matrix = writer.encode(
                    sample_user, BarcodeFormat.QR_CODE, 400, 400);
            Bitmap qrcode_bmp = toBitmap(matrix);
            ImageView iv = (ImageView) findViewById(R.id.grImage_id);
            iv.setImageResource(R.drawable.qr);
            iv.setImageBitmap(qrcode_bmp);

        } catch (WriterException e) {
            e.printStackTrace();
        }

        // Shows the points of the user
        pointText = (TextView) findViewById(R.id.Points);
        pointText.setText(userPoints+" Points");
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
        getMenuInflater().inflate(R.menu.profile, menu);
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
