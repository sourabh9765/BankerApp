package com.example.omkard.bankerapp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.omkard.bankerapp.Database.StudentTable;
import com.example.omkard.bankerapp.databinding.ActivityMainBinding;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Digits;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Validator.ValidationListener {

    boolean editMode = false;
    StudentModel studentModel;
    ActivityMainBinding binding;


    @NotEmpty(message = "Enter Name")
    private EditText mNameEditText;

    @Password(min = 8, scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE_SYMBOLS, message = "Invalid password")
    private EditText PEditText;

    @ConfirmPassword(message = "Passwords don't match")
    private EditText editcnfpassword;

    @Email(message = "Enter Valid mail id")
    private EditText editEmail;

    //@Password(min = 10,scheme = Password.Scheme.NUMERIC,message = "Please Enter Valid No")
    //private EditText editPhnNo;
    @Digits(integer = 10, message = "Should be 10 Digit No ")
    private EditText editPhnNo;


    private Button mAddEditButton;
    private Validator mAddEditValidator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);



        mNameEditText = (EditText) findViewById(R.id.edtName);
        PEditText = (EditText) findViewById(R.id.password);
        editcnfpassword = (EditText) findViewById(R.id.confirmpass);
        editEmail = (EditText) findViewById(R.id.email);
        editPhnNo = (EditText) findViewById(R.id.dob);
        mAddEditButton = (Button) findViewById(R.id.btnSave);


        mAddEditValidator = new Validator(this);
        mAddEditValidator.setValidationListener(this);


        mAddEditButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mAddEditValidator.validate();
            }
        });


        studentModel = getIntent().getParcelableExtra("student");


        if (studentModel != null) {
            binding.btnList.setVisibility(View.GONE);
            editMode = true;
            binding.edtName.setText(studentModel.getName());
            binding.email.setText(studentModel.getEmail());
            binding.dob.setText(studentModel.getDob());
        } else {
            binding.btnList.setOnClickListener(this);
        }

        binding.btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                mAddEditValidator.validate();
                break;

            case R.id.btnList:
                Intent intent = new Intent(this, StudentList.class);
                startActivity(intent);
                break;
        }

    }

    @Override
    public void onValidationSucceeded() {
        Toast.makeText(this, "Yay! we got it right!", Toast.LENGTH_SHORT).show();
        StudentModel studentModel = new StudentModel(binding.edtName.getText().toString(),
                binding.password.getText().toString(),
                binding.confirmpass.getText().toString(),
                binding.email.getText().toString(),
                binding.dob.getText().toString());
        StudentTable studentTable = new StudentTable(this);

        if (editMode) {
            studentModel.setId(this.studentModel.getId());
            studentTable.updateStudent(studentModel);
            Toast.makeText(MainActivity.this, "Student updated successfully.", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            studentTable.addStudent(studentModel);
            Toast.makeText(MainActivity.this, "Student added successfully.", Toast.LENGTH_SHORT).show();


        }
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }

}


