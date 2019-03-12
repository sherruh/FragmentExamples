package com.geektech.myapplication.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.geektech.myapplication.R;
import com.geektech.myapplication.interfaces.IOnClickListener;
import com.geektech.myapplication.models.Task;

public class TaskViewHolder extends RecyclerView.ViewHolder {

    private TextView title;
    private int taskId;
    private IOnClickListener mListener;

    public TaskViewHolder(@NonNull View itemView, IOnClickListener listener) {
        super(itemView);
        mListener = listener;
        title = itemView.findViewById(R.id.vh_task_title);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.clickOn(taskId);
            }
        });
    }

    public void onBind(Task task) {
        taskId = task.id;
        title.setText(task.title);
    }
}
