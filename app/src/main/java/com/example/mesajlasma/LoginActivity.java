package com.example.mesajlasma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    EditText istifadeciAdiEditText ;
    Button qeydOlButton;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        describing();
    }

    public void describing()
    {
        istifadeciAdiEditText = (EditText) findViewById(R.id.istifadeciAdiEditText);
        qeydOlButton = (Button) findViewById(R.id.qeydOlButton);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();
        qeydOlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = istifadeciAdiEditText.getText().toString();
                istifadeciAdiEditText.setText("");
                add(username);
            }
        });
    }

    public void add(final String istifadeciAdi)
    {
        reference.child("Istifadeciler").child(istifadeciAdi).child("istifadeciadi").setValue(istifadeciAdi).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Ugurlu giris olundu", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("istifadeciAdi",istifadeciAdi);
                    startActivity(intent);

                }
            }

        });


    }
}