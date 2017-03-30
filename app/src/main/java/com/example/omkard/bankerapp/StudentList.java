package com.example.omkard.bankerapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.omkard.bankerapp.Adapter.StudentAdapter;
import com.example.omkard.bankerapp.Database.StudentTable;
import com.example.omkard.bankerapp.Interface.OnButtonClickedListener;
import com.example.omkard.bankerapp.databinding.ActivityStudentListBinding;

import java.util.List;

public class StudentList extends AppCompatActivity {

    ActivityStudentListBinding binding;
    StudentAdapter adapter;
    List<StudentModel> list;
    StudentTable studentTable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_student_list);
    }


    @Override
    protected void onResume() {
        super.onResume();
        studentTable = new StudentTable(this);
        list = studentTable.getAllStudents();

        adapter = new StudentAdapter(this, list);
        binding.listView.setAdapter(adapter);
        adapter.setOnButtonClickedListener(new OnButtonClickedListener() {
            @Override
            public void onClick(View view, final int position) {
                switch (view.getId()) {
                    case R.id.btnEdit:
                        Intent intent = new Intent(StudentList.this, MainActivity.class);
                        intent.putExtra("student", list.get(position));
                        startActivity(intent);
                        break;

                    case R.id.btnDelete:
                        //delete
                        AlertDialog.Builder builder = new AlertDialog.Builder(
                                StudentList.this);

                        builder.setTitle("Delete record?")
                                .setMessage("Delete?")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        studentTable.deleteStudent(list.get(position).getId());
                                        Toast.makeText(StudentList.this, "Data deleted.", Toast.LENGTH_SHORT).show();
                                        list.remove(position);
                                        adapter.notifyDataSetChanged();
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                                .setNeutralButton("Cant Say", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });

                        AlertDialog alertDialog = builder.create();

                        alertDialog.show();

                        break;
                }
            }
        });
    }
}
