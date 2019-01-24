package com.rjz.RoomJavaExample;

import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;

// 4 of 5
public class StudentRepository {

    private StudentDao studentDao;
    private LiveData<List<Student>> studentList;

    public StudentRepository(Application application) {
        StudentRoomDataBase dataBase = StudentRoomDataBase.getInstance(application);
        this.studentDao = dataBase.getStudentDao();
        this.studentList = studentDao.getAllStudent();
    }

    LiveData<List<Student>> getAllWords() {
        return studentList;
    }

    public void insert(Student s) {
        studentDao.insert(s);
        // TODO NOTE for .allowMainThreadQueries() 2B of 2B
        // if not want to make query on main thread then
        // remove this line. you have to use Asynktask for it.

//        new insertAsyncTask(studentDao).execute(s);
    }

    public Student findStudent(int id) {
       return studentDao.findStudent(id);
    }

    public int delStudent(int id) {
       return studentDao.delStudent(id);
    }

    /*
    private static class insertAsyncTask extends AsyncTask<Student, Void, Void> {

        private StudentDao mAsyncTaskDao;

        insertAsyncTask(StudentDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Student... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
    */
}
