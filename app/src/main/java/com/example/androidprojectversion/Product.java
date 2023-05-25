package com.example.androidprojectversion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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

    EditText document_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        titleData = getIntent().getStringExtra("Title");
        priceData = getIntent().getStringExtra("Price");
        ImageData = getIntent().getStringExtra("Image");
        document_ID = findViewById(R.id.document_ID);
        ID = getIntent().getStringExtra("ID");





        Button addData = findViewById(R.id.addData);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDataToFirebase();
                //Toast.makeText(getApplication(),"Clicked",Toast.LENGTH_LONG).show();
            }
        });
        Button deleteData = findViewById(R.id.deleteData);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    deleteProduct(document_ID.getText().toString());
                    getAllUsers();
                }catch (Exception e){
                    Toast.makeText(Product.this,e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        });
        Button info = findViewById(R.id.showInfo);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAllUsers();
            }
        });
    }
    private void addDataToFirebase() {

        Map<String,Object> data = new HashMap<>();
        data.put(KEY_PRODUCT_ID,ID);
        data.put(KEY_PRODUCT_NAME,titleData);
        data.put(KEY_PRODUCT_PRICE,priceData);
        data.put(KEY_PRODUCT_IMAGE,ImageData);

        database
                .collection("product")
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        System.out.println(documentReference.getId());
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
    private void deleteProduct(String dID) {

        database.collection("product").document(dID).delete();
    }
    public void getAllUsers() {
        database.collection("product")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Spinner mySpinner = findViewById(R.id.document_Spinner);
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(Product.this, android.R.layout.simple_spinner_item);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                adapter.add(document.getId());
                                Log.d("Document ID: ", document.getId() + " => " + document.getData());
                            }
                            mySpinner.setAdapter(adapter);
                            mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    document_ID.setText(mySpinner.getSelectedItem().toString());
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {

                                }
                            });
                        } else {
                            Log.w("ERROR", "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}