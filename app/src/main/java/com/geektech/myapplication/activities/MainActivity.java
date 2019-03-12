package com.geektech.myapplication.activities;


import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.geektech.myapplication.R;
import com.geektech.myapplication.adapters.TaskAdapter;
import com.geektech.myapplication.fragments.AddNewTaskFragment;
import com.geektech.myapplication.fragments.TaskDetailsFragment;
import com.geektech.myapplication.fragments.TaskListFragment;
import com.geektech.myapplication.interfaces.IClickOnTask;
import com.geektech.myapplication.interfaces.IOnAddNewTask;
import com.geektech.myapplication.interfaces.IOnClickListener;
import com.geektech.myapplication.interfaces.IOnCloseDetails;
import com.geektech.myapplication.interfaces.IOnResultCreateTask;
import com.geektech.myapplication.models.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements IOnAddNewTask, IOnResultCreateTask, IClickOnTask, IOnCloseDetails
{
    TaskListFragment taskListFragment;
    AddNewTaskFragment addNewTaskFragment;
    TaskDetailsFragment taskDetailsFragment;

    final String addNewTaskTag="add_new_task";
    final String taskDetailTag="task_details";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        taskListFragment = new TaskListFragment();
        fragmentTransaction.add(R.id.fragment_container, taskListFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onCreateNewTask(int taskId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        addNewTaskFragment = AddNewTaskFragment.instance(taskId);
        fragmentTransaction.hide(taskListFragment);
        fragmentTransaction.add(R.id.fragment_container, addNewTaskFragment,addNewTaskTag);
        fragmentTransaction.commit();
    }

    @Override
    public void onTaskCreated(Task task) {
        taskListFragment.AddNewTask(task);
        removeFragment(addNewTaskFragment);
    }

    @Override
    public void onClickOnTask(Task task) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        taskDetailsFragment = TaskDetailsFragment.instance(task);
        fragmentTransaction.hide(taskListFragment);
        fragmentTransaction.add(R.id.fragment_container, taskDetailsFragment,taskDetailTag);
        fragmentTransaction.commit();
    }

    @Override
    public void onCloseDetails() {
        removeFragment(taskDetailsFragment);
    }

    @Override
    public void onBackPressed() {
        AddNewTaskFragment addNewTaskFragment=(AddNewTaskFragment) getSupportFragmentManager().findFragmentByTag(addNewTaskTag);
        TaskDetailsFragment taskDetailsFragment=(TaskDetailsFragment)getSupportFragmentManager().findFragmentByTag(taskDetailTag);

        if(addNewTaskFragment!=null){
            removeFragment(addNewTaskFragment);
        }else if(taskDetailsFragment!=null){
            removeFragment(taskDetailsFragment);
        }else{
            super.onBackPressed();
        }
    }

    public void removeFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.show(taskListFragment);
        fragmentTransaction.commit();
    }
}
