package com.geektech.myapplication.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geektech.myapplication.R;
import com.geektech.myapplication.adapters.TaskAdapter;
import com.geektech.myapplication.interfaces.IAddNewTaskFragment;
import com.geektech.myapplication.interfaces.IClickOnTask;
import com.geektech.myapplication.interfaces.IOnAddNewTask;
import com.geektech.myapplication.interfaces.IOnClickListener;
import com.geektech.myapplication.models.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskListFragment extends Fragment implements IOnClickListener, IAddNewTaskFragment {

    RecyclerView tasksList;
    TaskAdapter taskAdapter;

    List<Task> tasks;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_task_list, container, false);
        tasks = setTasks();
        initRecycler(v, tasks);
        initAddNewTask(v);
        return v;
    }

    void initRecycler(View view, List<Task> taskList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        taskAdapter = new TaskAdapter(taskList, this);
        tasksList = view.findViewById(R.id.tasks_recycler);
        tasksList.setAdapter(taskAdapter);
        tasksList.setLayoutManager(layoutManager);
    }

    void initAddNewTask(View view) {
        FloatingActionButton btn = view.findViewById(R.id.add_task_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentActivity activity = getActivity();
                if (activity instanceof IOnAddNewTask) {
                    ((IOnAddNewTask) activity).onCreateNewTask(tasks.size());
                } else {
                    Log.d("ololo", "Must implement IOnResultCreateTask");
                }
            }
        });
    }

    List<Task> setTasks() {
        List<Task> tasks = new ArrayList<>();
        return tasks;
    }


    @Override
    public void clickOn(int taskId) {
        Log.d("ololo", "task id: " + taskId);
        FragmentActivity activity = getActivity();
        if (activity instanceof IClickOnTask) {
            Task task = getTask(taskId);
            if (task != null) {
                ((IClickOnTask) activity).onClickOnTask(task);
            } else {
                Log.d("ololo", "Task is null : " + taskId);
            }
        } else {
            Log.d("ololo", "Must implement IOnResultCreateTask");
        }
    }

    Task getTask(int id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).id == id) {
                return tasks.get(i);
            }
        }
        return null;
    }

    @Override
    public void AddNewTask(Task task) {
        tasks.add(task);
    }
}
