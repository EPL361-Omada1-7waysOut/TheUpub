package com.example.georg.theupub;

import android.content.Context;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class AdministratorActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button mButton;
    private TextView outScan;
    private EditText addPointsText;
    private EditText remPointsText;
    private Button addPointsButton;
    private Button remPointsButton;

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

    }

    public void addPoints(EditText T){
        String points = T.getText().toString();
        int numPoints=0;
        if(points.equals("")||points.equals("type points")){
            numPoints=0;
        }else{
            //This will change after the database
            numPoints = Integer.parseInt(points);
            ProfileActivity.userPoints=numPoints+ProfileActivity.userPoints;
            T.setText("");
            Context context = getApplicationContext();
            CharSequence text = "Points Added";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void removePoints(){

    }

    public void editTextClick(EditText T){
        T.setText("");
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {
            String re = scanResult.getContents();
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
