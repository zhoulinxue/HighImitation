package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Name: User
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-12 14:02
 */
public class User implements Parcelable {
    private String userName;
    private String phoneNum;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userName);
        dest.writeString(this.phoneNum);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.userName = in.readString();
        this.phoneNum = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
