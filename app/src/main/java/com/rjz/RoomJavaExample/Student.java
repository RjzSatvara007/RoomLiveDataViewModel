package com.rjz.RoomJavaExample;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// 1 of 5
@Entity(tableName = "student_table")
public class Student {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    public int id = 0;

    @NonNull
    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "age")
    public int age = 0;

    public Student(String name,int age) {
        this.name = name;
        this.age = age;
    }
}
