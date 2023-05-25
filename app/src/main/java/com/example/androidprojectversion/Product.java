package com.example.androidprojectversion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class Product extends AppCompatActivity {

    private FirebaseFirestore database = FirebaseFirestore.getInstance();

    private static final String KEY_PRODUCT_ID= "ID";
    private static final String KEY_PRODUCT_NAME = "Title";
    private static final String KEY_PRODUCT_PRICE = "Price";
    //private static final String KEY_PRODUCT_DESCRIPTION = "Info";
    private static final String KEY_PRODUCT_IMAGE= "Image";

    String titleData;
    String priceData;
    String ID;
    String ImageData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        titleData = getIntent().getStringExtra("Title");
        priceData = getIntent().getStringExtra("Price");
        ImageData = getIntent().getStringExtra("Image");
        ID = getIntent().getStringExtra("ID");
        Button addData = findViewById(R.id.addData);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDataToFirebase();
                System.out.println(ID);
                //Toast.makeText(getApplication(),"Clicked",Toast.LENGTH_LONG).show();
            }
        });
        Button deleteData = findViewById(R.id.deleteData);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteProduct("1");
            }
        });
    }
    private void addDataToFirebase() {
        //Build Parameters Map
        Map<String,Object> data = new HashMap<>();
        data.put(KEY_PRODUCT_ID,ID);
        data.put(KEY_PRODUCT_NAME,titleData);
        data.put(KEY_PRODUCT_PRICE,priceData);
        data.put(KEY_PRODUCT_IMAGE,ImageData);

        //Create
        database
                .collection("product")
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(),"Product Created!!",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Error"+e,Toast.LENGTH_LONG).show();
                    }
                });
    }
    private void deleteProduct(String pid) {



    }
}