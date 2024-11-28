package com.example.luxevista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class Services extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        Button addDiningButton = findViewById(R.id.add_dining);
        addDiningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Services.this, Reservations.class);
                intent.putExtra("serviceName", "Dining and Culinary Experiences");
                startActivity(intent);
            }
        });

        Button addRecreationalButton = findViewById(R.id.add_recreational);
        addRecreationalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Services.this, Reservations.class);
                intent.putExtra("serviceName", "Recreational and Leisure Activities");
                startActivity(intent);
            }
        });

        Button addSpaButton = findViewById(R.id.add_spa);
        addSpaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Services.this, Reservations.class);
                intent.putExtra("serviceName", "Spa and Wellness Services");
                startActivity(intent);
            }
        });

    }
}
