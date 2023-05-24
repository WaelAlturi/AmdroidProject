package com.example.androidprojectversion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class Product extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        String titleData = getIntent().getStringExtra("Title");
        String priceData = getIntent().getStringExtra("Price");
        String ImageData = getIntent().getStringExtra("Image");

//
        Button addData = findViewById(R.id.addData);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplication(),"Clicked",Toast.LENGTH_LONG).show();
                System.out.println(titleData+"  =>>>>"+priceData+"  =>>>>"+ImageData);
            }
        });


    }
    private void addDataToFirebase() {

    }
}