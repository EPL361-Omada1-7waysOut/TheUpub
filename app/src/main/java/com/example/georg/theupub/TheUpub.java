package com.example.georg.theupub;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class TheUpub extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_upub);

        Button btnEnter=(Button) findViewById(R.id.EnterButton);
        btnEnter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
             startInfo();

            }
        });


    }

public void startInfo(){
    Intent intent=new Intent(this,TheMenu.class);
    startActivity(intent);
    this.finish();
}

}
