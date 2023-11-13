package com.example.lab2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class list_activity extends Activity {
    String data;
    //SharedPreferences saveData;
    //SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("shr", "ShareData");
        setContentView(R.layout.list_activity);

        Button button2 = findViewById(R.id.add);
        Button button3 = findViewById(R.id.del);

        ListView list1 = findViewById(R.id.list);
        EditText text1 = findViewById(R.id.editText);

        ArrayList<String> datalist = new ArrayList<String>();

        ArrayAdapter<String> TextAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_multiple_choice, datalist);

        list1.setAdapter(TextAdapter);
        Intent intent = new Intent(this, MainActivity.class);
        Bundle recData = this.getIntent().getExtras();

        String[] inData = recData.getStringArray("key");
        Collections.addAll(datalist, inData);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = text1.getText().toString();
                if(!data.isEmpty()) {
                    datalist.add(data);
                    text1.setText("");
                    TextAdapter.notifyDataSetChanged();
                }


            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SparseBooleanArray selected = list1.getCheckedItemPositions();

                int offset = 0;
                for (int i = 0; i < selected.size(); i++) {
                    if (selected.valueAt(i)) {
                        int position = selected.keyAt(i);
                        list1.setItemChecked(position, false);
                        datalist.remove(position - offset);
                        offset++;
                    }
                }
                TextAdapter.notifyDataSetChanged();
            }
        });
    }



}

