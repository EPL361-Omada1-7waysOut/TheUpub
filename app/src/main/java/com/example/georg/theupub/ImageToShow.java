package com.example.georg.theupub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageToShow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_to_show);
        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("intVariableName", 0);

        ImageView MyImage=(ImageView) findViewById(R.id.Image_to_show);
        MyImage.setImageResource(intValue);
    }
}
