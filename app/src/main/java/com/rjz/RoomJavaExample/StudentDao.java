package com.rjz.RoomJavaExample;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

// 2 of 5

@Dao
public interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Student word);

    @Query("Select * from student_table where id= :id")
    Student findStudent(int id);

    @Query("Delete from student_table where id= :id")
    int delStudent(int id); // it will returns how many records are deleted.

    @Query("Select * from student_table")
    LiveData<List<Student>> getAllStudent();

}
