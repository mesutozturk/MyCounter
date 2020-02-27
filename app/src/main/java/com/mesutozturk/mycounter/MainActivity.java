package com.mesutozturk.mycounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnCounter;
    int counter = 0;

    SharedPreferences preferences;  //saving variables persistant

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCounter = findViewById(R.id.btn_counter);

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());  // setting pref. object

        counter = preferences.getInt("counter", 0);
        btnCounter.setText(counter + "");

        btnCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                String counterText = counter + "";
                btnCounter.setText(counterText);
                preferences.edit().putInt("counter", counter).apply();
            }
        });

        btnCounter.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //my reset action
                counter = 0;
                String counterText = counter + "";
                btnCounter.setText(counterText);
                preferences.edit().putInt("counter", counter).apply();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu); // set menu to activity!!!!
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.app_settings) {
            Toast.makeText(this, "Open settings activity", Toast.LENGTH_SHORT).show();
            //Open settings activity
            Intent intent = new Intent(this,SettingsActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Selected other options", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
