/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.architecture.blueprints.todoapp.addedittask;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.architecture.blueprints.todoapp.R;
import com.example.android.architecture.blueprints.todoapp.addedittask.widget.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Main UI for the add task screen. Users can enter a task title and description.
 */
public class AddEditTaskFragment extends Fragment implements AddEditTaskContract.View, View.OnClickListener{

    public static final String ARGUMENT_EDIT_TASK_ID = "EDIT_TASK_ID";

    private AddEditTaskContract.Presenter mPresenter;

    private TextView mTitle;

    private TextView mDescription;

    private TextView mBorrowPerson;

    private TextView mOsType;

    private TextView mOsVersion;

    private TextView mDeviceResolution;

    private TextView mDeviceAssertNumber;

    private TextView mBorrowTime;

    private String mEditedTaskId;

    private RelativeLayout selectTime;

    private CustomDatePicker customDatePicker2;

    public String now;

    public static AddEditTaskFragment newInstance() {
        return new AddEditTaskFragment();
    }

    public AddEditTaskFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull AddEditTaskContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setTaskIdIfAny();

        FloatingActionButton fab =
                (FloatingActionButton) getActivity().findViewById(R.id.fab_edit_task_done);
        fab.setImageResource(R.drawable.ic_done);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNewTask()) {
                    mPresenter.createTask(
                            mTitle.getText().toString(),
                            mDescription.getText().toString(),
                            mBorrowPerson.getText().toString(),
                            mOsType.getText().toString(),
                            mOsVersion.getText().toString(),
                            mDeviceResolution.getText().toString(),
                            mDeviceAssertNumber.getText().toString(),
                            mBorrowTime.getText().toString());
                } else {
                    mPresenter.updateTask(
                            mTitle.getText().toString(),
                            mDescription.getText().toString(),
                            mBorrowPerson.getText().toString(),
                            mOsType.getText().toString(),
                            mOsVersion.getText().toString(),
                            mDeviceResolution.getText().toString(),
                            mDeviceAssertNumber.getText().toString(),
                            mBorrowTime.getText().toString());
                }

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.addtask_frag, container, false);
        mTitle = (TextView) root.findViewById(R.id.add_task_title);
        mDescription = (TextView) root.findViewById(R.id.add_task_description);
        mBorrowPerson = (TextView) root.findViewById(R.id.add_task_borrow_person);
        mOsType = (TextView) root.findViewById(R.id.add_task_os_type);
        mOsVersion = (TextView) root.findViewById(R.id.add_task_os_version);
        mDeviceResolution = (TextView) root.findViewById(R.id.add_task_device_resolution);
        mDeviceAssertNumber = (TextView) root.findViewById(R.id.add_task_device_assert_number);
        mBorrowTime = (TextView) root.findViewById(R.id.currentTime);
        selectTime = (RelativeLayout) root.findViewById(R.id.selectTime);
        selectTime.setOnClickListener(this);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        now = sdf.format(new Date());
        mBorrowTime.setText(now);

        setHasOptionsMenu(true);
        setRetainInstance(true);
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.selectTime:
                // 日期格式为yyyy-MM-dd HH:mm
                showDatePicker();
                break;
        }
    }

    @Override
    public void showEmptyTaskError() {
        Snackbar.make(mTitle, getString(R.string.empty_task_message), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showTasksList() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void setTitle(String title) {
        mTitle.setText(title);
    }

    @Override
    public void setDescription(String description) {
        mDescription.setText(description);
    }

    @Override
    public void setBorrowPerson(String borrowPerson) {
        mBorrowPerson.setText(borrowPerson);
    }

    @Override
    public void setOsType(String osType) {
        mOsType.setText(osType);
    }

    @Override
    public void setOsVersion(String osVersion) {
        mOsVersion.setText(osVersion);
    }

    @Override
    public void setDeviceResolution(String deviceResolution) {
        mDeviceResolution.setText(deviceResolution);
    }

    @Override
    public void setDeviceAssertNumber(String deviceAssertNumber) {
        mDeviceAssertNumber.setText(deviceAssertNumber);
    }

    @Override
    public void setBorrowTime(String borrowTime) {
        mBorrowTime.setText(borrowTime);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    private void setTaskIdIfAny() {
        if (getArguments() != null && getArguments().containsKey(ARGUMENT_EDIT_TASK_ID)) {
            mEditedTaskId = getArguments().getString(ARGUMENT_EDIT_TASK_ID);
        }
    }

    private boolean isNewTask() {
        return mEditedTaskId == null;
    }

    private void showDatePicker() {
        try {


            customDatePicker2 = new CustomDatePicker(getActivity(), new CustomDatePicker.ResultHandler() {
                @Override
                public void handle(String time) { // 回调接口，获得选中的时间
                    mBorrowTime.setText(time);
                }
            }, "2010-01-01 00:00", now); // 初始化日期格式：yyyy-MM-dd HH:mm
            customDatePicker2.showSpecificTime(true); // 显示时和分
            customDatePicker2.setIsLoop(true); // 允许循环滚动
            customDatePicker2.show(mBorrowTime.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
