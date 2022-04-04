package com.nayla.tuitionfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;



public class MainActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    public static String myUID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void studentLogin(View view) {
        SearchTuition.searchType = "Tutor";
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
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


    public void tutorLogin(View view) {
        SearchTuition.searchType = "Student";
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
