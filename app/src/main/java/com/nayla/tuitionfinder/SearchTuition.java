package com.nayla.tuitionfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchTuition extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    ArrayList<profile> list;
    public static String searchType="Tutor";
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tuition);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        recyclerView = (RecyclerView) findViewById(R.id.recylerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<profile>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int cnt=0;
                String type="guruji";
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    /*String name = dataSnapshot1.child("name").getValue().toString();
                    String institution = dataSnapshot1.child("institution").getValue().toString();
                    String department = dataSnapshot1.child("department").getValue().toString();
                    String address = dataSnapshot1.child("address").getValue().toString();
                    List<String> level = dataSnapshot1.child("level").getValue();
                    String subjects = dataSnapshot1.child("name").getValue().toString();*/

                    type = dataSnapshot1.child("type").getValue().toString();
                    if (type.compareTo(searchType)==0){
                        profile p = dataSnapshot1.getValue(profile.class);
                        p.setUID(dataSnapshot1.getKey());
                        list.add(p);
                        cnt++;
                    }
                }
                Toast.makeText(getApplicationContext(),cnt+" "+type+"s",Toast.LENGTH_SHORT).show();
                adapter = new MyAdapter(SearchTuition.this,list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Kaj hocchena",Toast.LENGTH_SHORT).show();
            }
        });

    }



    //Option menu
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
