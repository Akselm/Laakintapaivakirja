package com.example.laakintapaivakirja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class RemainderActivity extends AppCompatActivity {
    //public static final String EXTRA_MESSAGE = "name";
    //public static final String EXTRA_MESSAGE2 = "time";
    private Button button2; //Button variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remainder);

        button2 = (Button) findViewById(R.id.button2);
        TextView mn = findViewById(R.id.medicine_name); //Etsitään ui tekstikentät/napit ja tehdään niistä variableja
        TextView ta = findViewById(R.id.time_amount);
        String name = mn.getText().toString(); //Muutetaan tekstikenttä variablet vielä toiseen variable muotoon
        String time = ta.getText().toString();


        button2.setOnClickListener(new View.OnClickListener() { //Klikkauksella nappi toteuttaa uuden muistutuksen luomisen
            public void onClick(View view) {
                Reminder reminder = new Reminder("he", "jgh");

                Intent intent = new Intent(view.getContext(), MainActivity.class);

                intent.putExtra("NAME", reminder);
                intent.putExtra("method", 1);
                startActivity(intent);

            }
        });
    }
}


