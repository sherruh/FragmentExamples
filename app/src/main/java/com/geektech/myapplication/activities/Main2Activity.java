package com.geektech.myapplication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.geektech.myapplication.R;
import com.geektech.myapplication.models.Task;

public class Main2Activity extends AppCompatActivity {

    public static final String TASK_KEY = "task";

    EditText etTaskTitle;
    EditText etTaskDesc;
    EditText etTaskImage;

    String sTaskTitle;
    String sTaskDesc;
    String sTaskImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        etTaskTitle = findViewById(R.id.task_title_edit_text);
        etTaskDesc = findViewById(R.id.task_description_edit_text);
        etTaskImage = findViewById(R.id.task_image_edit_text);

        etTaskTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                sTaskTitle = s.toString();
            }
        });

        etTaskDesc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sTaskDesc = s.toString();
            }
        });

        etTaskImage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sTaskImage = s.toString();
            }
        });
    }

    public void onCreateTask(View v) {
        Task task = new Task(1, sTaskTitle, sTaskImage, sTaskDesc);
        Intent intent = new Intent();
        intent.putExtra(TASK_KEY, task);
        setResult(RESULT_OK, intent);
        finish();
    }
}
