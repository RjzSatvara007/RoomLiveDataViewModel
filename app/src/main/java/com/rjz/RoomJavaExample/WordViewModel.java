package com.rjz.RoomJavaExample;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

// 5 of 5
public class WordViewModel extends AndroidViewModel {

    private StudentRepository studentRepository;
    private LiveData<List<Student>> studentDataList;

    WordViewModel(Application application) {
        super(application);
        studentRepository = new StudentRepository(application);
        studentDataList = studentRepository.getAllWords();
    }

    LiveData<List<Student>> getAllStudent() {
        return studentDataList;
    }

    public void insert(Student student) {
        studentRepository.insert(student);
    }

    public Student findStudent(int id) {
        return studentRepository.findStudent(id);
    }

    public int delStudent(int id) {
        return studentRepository.delStudent(id);
    }
}
