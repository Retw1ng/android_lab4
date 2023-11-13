package com.example.lab2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String[] sendData = new String[]{"Проверка", "передачи", "информации", "в", "вторую активность"};
    EditText inName;
    EditText inPsw;
    SharedPreferences settingEnter;
    SharedPreferences.Editor editor;

    private static final String NAME = "name";
    private static final String PASSWORD = "password";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_main);

        settingEnter = this.getSharedPreferences("shr", Context.MODE_PRIVATE);


        inName = findViewById(R.id.inputName);
        inPsw = findViewById(R.id.inputPassword);

        Intent intent = new Intent(this, list_activity.class);
        Button button1 = findViewById(R.id.button);
        Bundle data = new Bundle();
        data.putStringArray("key", sendData);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtras(data);
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.i("ON PAUSE", "Pause");
        Log.i("ON PAUSE", "Pause");
        editor = settingEnter.edit();
        editor.putString(NAME, inName.getText().toString());
        editor.apply();
        editor.putString(PASSWORD, inPsw.getText().toString());
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ON RESUME", "Resume");
        inName.setText(settingEnter.getString(NAME, ""));
        inPsw.setText(settingEnter.getString(PASSWORD,""));
    }

    @Override
    protected void onDestroy() {
        settingEnter = this.getSharedPreferences("shr", Context.MODE_PRIVATE);
        boolean a = this.deleteSharedPreferences("shr");
        super.onDestroy();
        Log.i("ON DESTROY", "Destroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("ON STOP", "Stop");
    }
}