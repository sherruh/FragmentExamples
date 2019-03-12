package com.geektech.myapplication.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geektech.myapplication.R;
import com.geektech.myapplication.interfaces.IOnClickListener;
import com.geektech.myapplication.models.Task;
import com.geektech.myapplication.viewholders.TaskViewHolder;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {

    private List<Task> taskList;
    private IOnClickListener mListener;

    public TaskAdapter(List<Task> list, IOnClickListener listener) {
        taskList = list;
        mListener = listener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.view_holder_task, viewGroup, false);
        TaskViewHolder vh = new TaskViewHolder(v, mListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder taskViewHolder, int i) {
        taskViewHolder.onBind(taskList.get(i));
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}
