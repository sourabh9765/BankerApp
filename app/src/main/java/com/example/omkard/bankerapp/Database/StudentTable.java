package com.example.omkard.bankerapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.omkard.bankerapp.Constant.Constants;
import com.example.omkard.bankerapp.StudentModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OmkarD on 3/26/2017.
 */
public class StudentTable extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "students";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_CNFPASSWORD = "cnfpassword";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_DOB = "dob";




    private static final int COL_ID = 0;
    private static final int COL_NAME = 1;
    private static final int COL_PASSWORD = 4;
    private static final int COL_CNFPASSWORD = 5;
    private static final int COL_EMAIL = 2;
    private static final int COL_DOB = 3;
    private static final String TAG = "-- StudentTable";

    Context context;

    public StudentTable(Context context) {
        super(context, TABLE_NAME, null, Constants.DB_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_PASSWORD + " TEXT,"
                + KEY_CNFPASSWORD + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_DOB + " TEXT " + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
        Log.d(TAG, "onCreate: called");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade: called");

    }



    public void addStudent(StudentModel student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getName());
        values.put(KEY_PASSWORD, student.getPassword());
        values.put(KEY_CNFPASSWORD, student.getCnfpassword());
        values.put(KEY_EMAIL, student.getEmail());
        values.put(KEY_DOB, student.getDob());



        db.insert(TABLE_NAME, null, values);
        db.close();
    }



    public int updateStudent(StudentModel student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getName());
        values.put(KEY_EMAIL, student.getEmail());
        values.put(KEY_DOB, student.getDob());

        // updating row
        return db.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[]{String.valueOf(student.getId())});
    }


    public void deleteStudent(int studentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[]{String.valueOf(studentId)});
        db.close();
    }


    public StudentModel getStudent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_ID,
                        KEY_NAME, KEY_EMAIL, KEY_DOB}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            return new StudentModel(cursor.getInt(COL_ID),
                    cursor.getString(COL_NAME), cursor.getString(COL_EMAIL), cursor.getString(COL_DOB));
        }

        return null;
    }


    public List<StudentModel> getAllStudents() {
        List<StudentModel> students = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                StudentModel student = new StudentModel();
                student.setId(Integer.parseInt(cursor.getString(COL_ID)));
                student.setName(cursor.getString(COL_NAME));
                student.setEmail(cursor.getString(COL_EMAIL));
                student.setDob(cursor.getString(COL_DOB));

                students.add(student);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return students;
    }
}