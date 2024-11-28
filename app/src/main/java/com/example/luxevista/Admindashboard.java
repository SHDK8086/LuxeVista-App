package com.example.luxevista;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Admindashboard extends AppCompatActivity {

    private EditText titleEditText, descriptionEditText, timeEditText;
    private Button saveButton;
    private ImageView logoutButton;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindashboard);

        databaseReference = FirebaseDatabase.getInstance().getReference("Notifications");
        firebaseAuth = FirebaseAuth.getInstance();

        titleEditText = findViewById(R.id.notificationTitle);
        descriptionEditText = findViewById(R.id.notificationDescription);
        timeEditText = findViewById(R.id.sendTime);
        saveButton = findViewById(R.id.saveButton);
        logoutButton = findViewById(R.id.logout);

        saveButton.setOnClickListener(v -> {
            String title = titleEditText.getText().toString().trim();
            String description = descriptionEditText.getText().toString().trim();
            String sendTime = timeEditText.getText().toString().trim();

            if (title.isEmpty() || description.isEmpty() || sendTime.isEmpty()) {
                Toast.makeText(Admindashboard.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            saveNotificationToFirebase(title, description, sendTime);
        });

        logoutButton.setOnClickListener(v -> {
            firebaseAuth.signOut(); // Log out the user
            Toast.makeText(Admindashboard.this, "Logged out successfully", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(Admindashboard.this, AdminSignIn.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }

    private void saveNotificationToFirebase(String title, String description, String sendTime) {
        String notificationId = databaseReference.push().getKey();

        HashMap<String, String> notificationData = new HashMap<>();
        notificationData.put("Title", title);
        notificationData.put("Description", description);
        notificationData.put("SendTime", sendTime);

        if (notificationId != null) {
            databaseReference.child(notificationId).setValue(notificationData)
                    .addOnSuccessListener(unused -> {
                        Toast.makeText(Admindashboard.this, "Notification Saved", Toast.LENGTH_SHORT).show();
                        titleEditText.setText("");
                        descriptionEditText.setText("");
                        timeEditText.setText("");
                    })
                    .addOnFailureListener(e ->
                            Toast.makeText(Admindashboard.this, "Failed to Save: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        }
    }
}
