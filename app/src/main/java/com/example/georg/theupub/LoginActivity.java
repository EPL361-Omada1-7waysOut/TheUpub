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
