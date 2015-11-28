package com.example.georg.theupub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    private static Boolean ISadmin=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnAdmin=(Button) findViewById(R.id.ENterAdm);
        btnAdmin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ISadmin = true;
                startAdmin();
            }
        });


        Button btnProfile=(Button) findViewById(R.id.ENterProf);


        btnProfile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ISadmin = false;
                startProfile();


            }
        });

    }


    public static void setISadmin(){
        ISadmin=!ISadmin;

    }

    public static synchronized  boolean getISadmin(){
       return ISadmin;

    }

    public void startAdmin(){
        Intent intent=new Intent(this,AdministratorActivity.class);
        startActivity(intent);
        this.finish();
    }


    public void startProfile(){
        Intent intent=new Intent(this,ProfileActivity.class);
        startActivity(intent);
        this.finish();
    }


}
