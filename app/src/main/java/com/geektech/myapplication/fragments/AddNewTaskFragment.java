package com.geektech.myapplication.fragments;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.text.format.DateFormat;
import android.widget.TextView;

import com.geektech.myapplication.R;
import com.geektech.myapplication.interfaces.IOnAddNewTask;
import com.geektech.myapplication.interfaces.IOnResultCreateTask;
import com.geektech.myapplication.models.Task;

import java.util.Calendar;
import java.util.Date;


public class AddNewTaskFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    public static final String TASK_KEY = "task";

    EditText etTaskTitle;
    EditText etTaskDesc;
    EditText etTaskImage;
    TextView tvEndDate;

    String sTaskTitle;
    String sTaskDesc;
    String sTaskImage;

    int taskId;

    DatePickerDialog datePickerDialog;
    Date endDate;

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

        etTaskTitle.addTextChangedListener(new MyTextWatcher(etTaskTitle));
        etTaskDesc.addTextChangedListener(new MyTextWatcher(etTaskDesc));
        etTaskImage.addTextChangedListener(new MyTextWatcher(etTaskImage));

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

        tvEndDate=view.findViewById(R.id.task_end_date_text);
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONDAY);
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog=new DatePickerDialog(getContext(),this,year,month,day);
        Button pickEndDate=view.findViewById(R.id.task_end_date_pick);
        pickEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        DateFormat df=new DateFormat();
        endDate=new Date(year-1900,month,dayOfMonth);
        String endDateStr=df.format("yyyy.MM.dd",endDate).toString();
        tvEndDate.setText(endDateStr);
    }

    private class MyTextWatcher implements TextWatcher{

        private View v;

        private MyTextWatcher(View view){
            v=view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch(v.getId()){
                case R.id.task_description_edit_text:
                    sTaskDesc=s.toString();
                    break;
                case  R.id.task_title_edit_text:
                    sTaskTitle=s.toString();
                    break;
                case R.id.task_image_edit_text:
                    sTaskImage=s.toString();
                    break;
            }
        }
    }
}
