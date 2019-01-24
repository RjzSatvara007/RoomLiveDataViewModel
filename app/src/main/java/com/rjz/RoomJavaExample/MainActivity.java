package com.rjz.RoomJavaExample;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final WordViewModel wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        final TextView textView = findViewById(R.id.tvTest);

        findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText test = findViewById(R.id.edtName);
                wordViewModel.insert(new Student(test.getText().toString(), getRandomAge()));
            }
        });

        findViewById(R.id.btnFind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText test = findViewById(R.id.edtFind);
                Student student = wordViewModel.findStudent(Integer.parseInt(test.getText().toString()));
                if (student != null)
                    Toast.makeText(MainActivity.this, student.name + " is name.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "No records found.", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btnDel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText test = findViewById(R.id.edtFind);
                Student student = wordViewModel.findStudent(Integer.parseInt(test.getText().toString()));
                if (student != null) {
                    int isDeleted = wordViewModel.delStudent(Integer.parseInt(test.getText().toString()));
                    if (isDeleted > 0)
                        Toast.makeText(MainActivity.this, "Record deleted Successfully", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MainActivity.this, "Record Not deleted.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "No records found for this ID.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        wordViewModel.getAllStudent().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(@Nullable List<Student> studentList) {
                textView.setText("");
                for (Student student : studentList) {
                    textView.append(student.id + " " + student.name + " " + student.age + "\n");
                }
            }
        });

    }

    private int getRandomAge() {
        final int min = 5;
        final int max = 25;
        final int random = new Random().nextInt((max - min) + 1) + min;
        return random;
    }
}
