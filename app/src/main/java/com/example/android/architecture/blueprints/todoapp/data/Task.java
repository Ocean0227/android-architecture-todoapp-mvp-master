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

package com.example.android.architecture.blueprints.todoapp.data;

import android.support.annotation.Nullable;

import com.google.common.base.Objects;

import java.util.UUID;

/**
 * Immutable model class for a Task.
 */
public final class Task {

    private final String mId;

    @Nullable
    private final String mTitle;

    @Nullable
    private final String mDescription;

    private final boolean mCompleted;

    @Nullable
    private final String mBorrowPerson;

    @Nullable
    private final String mOsType;

    @Nullable
    private final String mOsVersion;

    @Nullable
    private final String mDeviceResolution;

    @Nullable
    private final String mDeviceAssertNumber;

    /**
     * Use this constructor to create a new active Task.
     *
     * @param title
     * @param description
     */
    public Task(@Nullable String title, @Nullable String description, @Nullable String borrowPerson,
                @Nullable String osType, @Nullable String osVersion,
                @Nullable String deviceResolution, @Nullable String deviceAssertNumber) {
        mId = UUID.randomUUID().toString();
        mTitle = title;
        mDescription = description;
        mCompleted = false;

        mBorrowPerson = borrowPerson;
        mOsType = osType;
        mOsVersion = osVersion;
        mDeviceResolution = deviceResolution;
        mDeviceAssertNumber = deviceAssertNumber;
    }

    /**
     * Use this constructor to create an active Task if the Task already has an id (copy of another
     * Task).
     *
     * @param title
     * @param description
     * @param id of the class
     */
    public Task(@Nullable String title, @Nullable String description, String id,
                @Nullable String borrowPerson, @Nullable String osType, @Nullable String osVersion,
                @Nullable String deviceResolution, @Nullable String deviceAssertNumber) {
        mId = id;
        mTitle = title;
        mDescription = description;
        mCompleted = false;

        mBorrowPerson = borrowPerson;
        mOsType = osType;
        mOsVersion = osVersion;
        mDeviceResolution = deviceResolution;
        mDeviceAssertNumber = deviceAssertNumber;
    }

    /**
     * Use this constructor to create a new completed Task.
     *
     * @param title
     * @param description
     * @param completed
     */
    public Task(@Nullable String title, @Nullable String description, boolean completed,
                @Nullable String borrowPerson, @Nullable String osType, @Nullable String osVersion,
                @Nullable String deviceResolution, @Nullable String deviceAssertNumber) {
        mId = UUID.randomUUID().toString();
        mTitle = title;
        mDescription = description;
        mCompleted = completed;

        mBorrowPerson = borrowPerson;
        mOsType = osType;
        mOsVersion = osVersion;
        mDeviceResolution = deviceResolution;
        mDeviceAssertNumber = deviceAssertNumber;
    }

    /**
     * Use this constructor to specify a completed Task if the Task already has an id (copy of
     * another Task).
     *
     * @param title
     * @param description
     * @param id
     * @param completed
     */
    public Task(@Nullable String title, @Nullable String description, String id, boolean completed,
                @Nullable String borrowPerson, @Nullable String osType, @Nullable String osVersion,
                @Nullable String deviceResolution, @Nullable String deviceAssertNumber) {
        mId = id;
        mTitle = title;
        mDescription = description;
        mCompleted = completed;

        mBorrowPerson = borrowPerson;
        mOsType = osType;
        mOsVersion = osVersion;
        mDeviceResolution = deviceResolution;
        mDeviceAssertNumber = deviceAssertNumber;
    }

    public String getId() {
        return mId;
    }

    @Nullable
    public String getTitle() {
        return mTitle;
    }

    @Nullable
    public String getTitleForList() {
        if (mTitle != null && !mTitle.equals("")) {
            return mTitle;
        } else {
            return mDescription;
        }
    }

    @Nullable
    public String getDescription() {
        return mDescription;
    }

    @Nullable
    public String getBorrowPerson() {
        return mBorrowPerson;
    }

    @Nullable
    public String getOsType() {
        return mOsType;
    }

    @Nullable
    public String getOsVersion() {
        return mOsVersion;
    }

    @Nullable
    public String getDeviceResolution() {
        return mDeviceResolution;
    }

    @Nullable
    public String getDeviceAssertNumber() {
        return mDeviceAssertNumber;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public boolean isActive() {
        return !mCompleted;
    }

    public boolean isEmpty() {
        return (mTitle == null || "".equals(mTitle)) &&
                (mDescription == null || "".equals(mDescription)) &&
                (mBorrowPerson == null || "".equals(mBorrowPerson)) &&
                (mOsType == null || "".equals(mOsType)) &&
                (mOsVersion == null || "".equals(mOsVersion)) &&
                (mDeviceResolution == null || "".equals(mDeviceResolution)) &&
                (mDeviceAssertNumber == null || "".equals(mDeviceAssertNumber));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equal(mId, task.mId) &&
                Objects.equal(mTitle, task.mTitle) &&
                Objects.equal(mDescription, task.mDescription) &&
                Objects.equal(mBorrowPerson, task.mBorrowPerson) &&
                Objects.equal(mOsType, task.mOsType) &&
                Objects.equal(mOsVersion, task.mOsVersion) &&
                Objects.equal(mDeviceResolution, task.mDeviceResolution) &&
                Objects.equal(mDeviceAssertNumber, task.mDeviceAssertNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mId, mTitle, mDescription);
    }

    @Override
    public String toString() {
        return "Task with title " + mTitle;
    }
}
