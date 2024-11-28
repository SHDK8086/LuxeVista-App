package com.example.luxevista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class StandardRooms extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard_rooms);

        GridLayout roomGrid = findViewById(R.id.roomGrid);


        Spinner roomTypeSpinner = findViewById(R.id.roomTypeSpinner);

        roomTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedType = parent.getItemAtPosition(position).toString();

                for (int i = 0; i < roomGrid.getChildCount(); i++) {
                    CardView cardView = (CardView) roomGrid.getChildAt(i);
                    TextView roomTypeText = cardView.findViewById(R.id.roomTypeText);

                    if (selectedType.equals("All") || roomTypeText.getText().toString().equals(selectedType)) {
                        cardView.setVisibility(View.VISIBLE);
                    } else {
                        cardView.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        for (int i = 0; i < roomGrid.getChildCount(); i++) {
            CardView cardView = (CardView) roomGrid.getChildAt(i);

            ImageView plusIcon = cardView.findViewById(R.id.plus);

            final String roomName = ((TextView) cardView.findViewById(R.id.roomTypeText)).getText().toString();
            final String roomPrice = ((TextView) ((LinearLayout) cardView.getChildAt(0)).getChildAt(2)).getText().toString();

            plusIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(StandardRooms.this, Booking.class);
                    intent.putExtra("room_name", roomName);
                    intent.putExtra("room_price", roomPrice);
                    startActivity(intent);
                }
            });
        }
    }
}
