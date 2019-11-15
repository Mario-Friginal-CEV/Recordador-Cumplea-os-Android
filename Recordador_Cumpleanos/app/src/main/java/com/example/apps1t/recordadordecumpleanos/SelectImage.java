package com.example.apps1t.recordadordecumpleanos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.squareup.picasso.Picasso;

public class SelectImage extends AppCompatActivity {

    ImageButton image1, image2, image3, image4, image5;
    String image1URL = "http://docs.google.com/uc?export=open&id=17vlrZbE7z5vc-MAK-C8SBNQsNLhOcoRe";
    String image2URL = "http://docs.google.com/uc?export=open&id=1x8yH36yVP22_C7TX-w28-EOG4KuzKWTn";
    String image3URL = "http://docs.google.com/uc?export=open&id=1UJLP6L_7NnjSA4vA-_7knyofc-M9FWwX";
    String image4URL = "http://docs.google.com/uc?export=open&id=1_vV36NlPnEgIaJLxVPnOiQzxZtROLljE";
    String image5URL = "http://docs.google.com/uc?export=open&id=1NUEe6F52qnkBc7lFUXubCbLZdtj2h_Qi";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_image);


        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);

        Picasso.get().load(image1URL).into(image1);
        Picasso.get().load(image2URL).into(image2);
        Picasso.get().load(image3URL).into(image3);
        Picasso.get().load(image4URL).into(image4);
        Picasso.get().load(image5URL).into(image5);

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                String currentImage = image1URL;
                intent.putExtra("Image",currentImage);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                String currentImage = image2URL;
                intent.putExtra("Image",currentImage);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                String currentImage = image3URL;
                intent.putExtra("Image",currentImage);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                String currentImage = image4URL;
                intent.putExtra("Image",currentImage);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                String currentImage = image5URL;
                intent.putExtra("Image",currentImage);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }



}
