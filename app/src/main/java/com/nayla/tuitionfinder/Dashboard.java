package com.nayla.tuitionfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class Dashboard extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String myType = "X";
    String myName = "X";
    DatabaseReference databaseReference;
    TextView welcomeMsg,dashboard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        welcomeMsg = (TextView) findViewById(R.id.textView10);
        dashboard = (TextView) findViewById(R.id.textView);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        String myUid = firebaseUser.getUid();
        MainActivity.myUID = myUid;
        databaseReference = FirebaseDatabase.getInstance().getReference().child(myUid);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("name")&&dataSnapshot.hasChild("type")) {
                    myType = dataSnapshot.child("type").getValue().toString();
                    myName = dataSnapshot.child("name").getValue().toString();
                    welcomeMsg.setText("Welcome " + myName);
                    dashboard.setText(myType + " Dashboard");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        checkEmailVerification();
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
    private void checkEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(!firebaseUser.isEmailVerified()){
            Toast.makeText(Dashboard.this,"Please check your email for verification",Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
            finish();
        }
    }

    public void updateProfile(View view) {
        Intent intent = new Intent(this,UpdateProfile.class);
        startActivity(intent);
    }

    public void exitBtn(View view) {
        finish();
    }

    public void searchTuition(View view) {
        if (myType.compareTo(SearchTuition.searchType)==0){
            Toast.makeText(this, "Tumi authorized na baba!\nOpposite type search koro ;p", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(this, SearchTuition.class);
            startActivity(intent);
        }
    }
}
