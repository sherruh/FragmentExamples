package com.geektech.myapplication.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.geektech.myapplication.R;
import com.geektech.myapplication.interfaces.IOnCloseDetails;
import com.geektech.myapplication.models.Task;
import com.squareup.picasso.Picasso;

public class TaskDetailsFragment extends Fragment {

    TextView taskTitle;
    TextView taskDescription;
    ImageView imageView;

    Task task;

    public static TaskDetailsFragment instance(Task task) {
        TaskDetailsFragment fragment = new TaskDetailsFragment();
        fragment.task = task;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_task_details, container, false);
        taskTitle = v.findViewById(R.id.task_details_title);
        taskDescription = v.findViewById(R.id.task_details_description);
        imageView = v.findViewById(R.id.task_details_image);

        Button btn = v.findViewById(R.id.task_details_close);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentActivity activity = getActivity();
                if (activity instanceof IOnCloseDetails) {
                    ((IOnCloseDetails) activity).onCloseDetails();
                } else {
                    Log.d("ololo", "Must implement IOnCloseDetails");
                }
            }
        });

        taskTitle.setText(task.title);
        taskDescription.setText(task.description);

        Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(imageView);

        return v;
    }
}
