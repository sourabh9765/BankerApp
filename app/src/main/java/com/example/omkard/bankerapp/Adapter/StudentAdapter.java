package com.example.omkard.bankerapp.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.omkard.bankerapp.Interface.OnButtonClickedListener;
import com.example.omkard.bankerapp.R;
import com.example.omkard.bankerapp.StudentModel;
import com.example.omkard.bankerapp.databinding.ListItemStudentBinding;
import com.example.omkard.bankerapp.Database.StudentTable;


import java.util.List;

/**
 * Created by Felix_ITS on 20-03-2017.
 */

public class StudentAdapter extends BaseAdapter {

    LayoutInflater inflater;
    List<StudentModel> list;
    ListItemStudentBinding binding;
    StudentTable studentTable;
    Context context;
    OnButtonClickedListener onButtonClickedListener;

    public StudentAdapter(Context context, List<StudentModel> list) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list = list;
        this.studentTable = studentTable;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public StudentModel getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        binding = DataBindingUtil.inflate(inflater, R.layout.list_item_student, viewGroup, false);
        final StudentModel studentModel = list.get(i);
        binding.setStudentModel(studentModel);

        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonClickedListener.onClick(view, i);
            }
        });

        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonClickedListener.onClick(view, i);
            }
        });
        return binding.getRoot();
    }

    public void setOnButtonClickedListener(OnButtonClickedListener onButtonClickedListener) {
        this.onButtonClickedListener = onButtonClickedListener;
    }
}
