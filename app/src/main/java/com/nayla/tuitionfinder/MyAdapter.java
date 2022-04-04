package com.nayla.tuitionfinder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<profile> profiles;

    public MyAdapter(Context context, ArrayList<profile> profiles) {
        this.context = context;
        this.profiles = profiles;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.name.setText(profiles.get(position).getName());
        holder.institution.setText(profiles.get(position).getInstitution());
        holder.department.setText(profiles.get(position).getDepartment());
        holder.address.setText(profiles.get(position).getAddress());
        holder.level.setText(profiles.get(position).getLevel());
        holder.subject.setText(profiles.get(position).getSubjects());
        holder.salary.setText(profiles.get(position).getSalary());
        holder.phone.setText(profiles.get(position).getPhone());
        holder.uid.setText(profiles.get(position).getUID());

        holder.visitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewProfile.uid = holder.uid.getText().toString();
                ViewProfile.name = holder.name.getText().toString();
                ViewProfile.subjectList = holder.subject.getText().toString();
                ViewProfile.levelList = holder.level.getText().toString();
                Intent intent = new Intent(v.getContext(),ViewProfile.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,institution,department,level,subject,salary,phone,address,uid;
        Button visitBtn;
         public MyViewHolder(View inflate) {
             super(inflate);
             name = (TextView) inflate.findViewById(R.id.textView1);
             institution = (TextView) inflate.findViewById(R.id.textView2);
             department = (TextView) inflate.findViewById(R.id.textView3);
             address = (TextView) inflate.findViewById(R.id.textView4);
             level = (TextView) inflate.findViewById(R.id.textView5);
             subject = (TextView) inflate.findViewById(R.id.textView6);
             salary = (TextView) inflate.findViewById(R.id.textView7);
             phone = (TextView) inflate.findViewById(R.id.textView8);
             uid = (TextView) inflate.findViewById(R.id.textView9);
             visitBtn = (Button) inflate.findViewById(R.id.goToProfileBtn);
        }
    }


}
