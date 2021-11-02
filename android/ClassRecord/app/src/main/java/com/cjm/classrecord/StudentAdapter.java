package com.cjm.classrecord;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cjm.classrecord.bean.StudentInfo;
import com.cjm.classrecord.interfaces.StudentInfoClickListener;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder>{
    private List<StudentInfo> students;
    private Context context;
    private StudentInfoClickListener listener;

    public StudentAdapter(List<StudentInfo> students) {
        this.students = students;
    }

    public void setStudentInfoClickListener(StudentInfoClickListener listener) {
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View itemView =
                LayoutInflater.from(context).inflate(R.layout.item_student, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final StudentInfo student = students.get(position);
        holder.tv_name.setText(student.getName());
        holder.tv_grade.setText(student.getGrade());
        holder.tv_subject.setText(student.getSubject());
        holder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onStudentInfoClick(student.getId(), student.getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View mItemView;
        TextView tv_name, tv_grade, tv_subject;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemView = itemView;
            tv_name = itemView.findViewById(R.id.item_tv_name);
            tv_grade = itemView.findViewById(R.id.item_tv_grade);
            tv_subject = itemView.findViewById(R.id.item_tv_describe);
        }
    }
}
