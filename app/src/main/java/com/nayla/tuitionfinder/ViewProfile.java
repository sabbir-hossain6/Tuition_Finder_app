package com.nayla.tuitionfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ViewProfile extends AppCompatActivity {
    public static String uid;
    public static String name;
    String email;
    String address;
    String institution;
    String department;
    public static String subjectList;
    public static String levelList;
    String salary;
    String phone;
    String gender;
    String skills;
    int hour;
    TextView tvName, tvIns, tvDept, tvAddress, tvSub,tvClass,tvSkills,tvSalary,tvPhone;

    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        tvName = (TextView) findViewById(R.id.tvName);
        tvDept = (TextView) findViewById(R.id.tvDepartment);
        tvIns = (TextView) findViewById(R.id.tvIns);
        tvAddress = (TextView) findViewById(R.id.tvAddress);
        tvSub = (TextView) findViewById(R.id.tvSubjects);
        tvClass = (TextView) findViewById(R.id.tvLevels);
        tvSkills = (TextView) findViewById(R.id.tvSkills);
        tvSalary = (TextView) findViewById(R.id.tvSalary);
        tvPhone = (TextView) findViewById(R.id.tvPhone);

        tvName.setText(name);
        tvSub.setText(subjectList);
        tvClass.setText(levelList);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child(uid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name = dataSnapshot.child("name").getValue().toString();
                email = dataSnapshot.child("email").getValue().toString();
                gender = dataSnapshot.child("gender").getValue().toString();
                address = dataSnapshot.child("address").getValue().toString();
                department = dataSnapshot.child("department").getValue().toString();
                institution = dataSnapshot.child("institution").getValue().toString();
                hour = Integer.parseInt(dataSnapshot.child("hour").getValue().toString());
                salary = dataSnapshot.child("salary").getValue().toString();
                phone = dataSnapshot.child("phone").getValue().toString();
                skills = dataSnapshot.child("skills").getValue().toString();

                tvName.setText(name);
                tvAddress.setText("Address: "+address);
                tvDept.setText("Department: "+department);
                tvIns.setText(institution);
                tvSalary.setText("Expected Salary: "+salary);
                tvSkills.setText("Skills: "+skills);
                tvPhone.setText("Phone: "+phone);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Toast.makeText(this,subjectList,Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logOutMenu:{
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(this,MainActivity.class));
            }
            case R.id.goToHome:{
                finish();
                return true;
            }
            case R.id.myProfile:{
                ViewProfile.uid = MainActivity.myUID;
                startActivity(new Intent(this,ViewProfile.class));
                return true;
            }
            case R.id.changeProfile:{
                //go to profile based upon profile type
                startActivity(new Intent(this,UpdateProfile.class));
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
