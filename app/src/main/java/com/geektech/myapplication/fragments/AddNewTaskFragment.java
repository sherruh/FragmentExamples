package com.geektech.myapplication.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.geektech.myapplication.R;
import com.geektech.myapplication.interfaces.IOnAddNewTask;
import com.geektech.myapplication.interfaces.IOnResultCreateTask;
import com.geektech.myapplication.models.Task;

public class AddNewTaskFragment extends Fragment {

    public static final String TASK_KEY = "task";

    EditText etTaskTitle;
    EditText etTaskDesc;
    EditText etTaskImage;

    String sTaskTitle;
    String sTaskDesc;
    String sTaskImage;

    int taskId;

    public static AddNewTaskFragment instance(int taskId) {
        AddNewTaskFragment fragment = new AddNewTaskFragment();
        fragment.taskId = taskId;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create_task, container, false);
        initElements(v);
        return v;
    }

    void initElements(View view) {
        etTaskTitle = view.findViewById(R.id.task_title_edit_text);
        etTaskDesc = view.findViewById(R.id.task_description_edit_text);
        etTaskImage = view.findViewById(R.id.task_image_edit_text);

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

        Button btn = view.findViewById(R.id.task_done_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentActivity activity = getActivity();
                if (activity instanceof IOnResultCreateTask) {
                    Task task = new Task(taskId, sTaskTitle, sTaskImage, sTaskDesc);
                    ((IOnResultCreateTask) activity).onTaskCreated(task);
                } else {
                    Log.d("ololo", "Must implement IOnResultCreateTask");
                }
            }
        });
    }
}
