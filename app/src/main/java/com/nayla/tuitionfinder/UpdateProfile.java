package com.nayla.tuitionfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class UpdateProfile extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String userId, email, password, username, institution, department, result, address, phone, picture, skills, hour, salary, gender, type = "Student";
    int vacancy, age=0;
    List<String> subjectList;
    List<String> levelList;
    EditText etname,etaddress,etphone,etinstituition,etdepartment,etresult,etskills,etsalary,ethour,etvacancy,etnid,etage;
    Spinner spgender;
    ArrayAdapter<CharSequence> spAdapter;
    RadioGroup typeGroup;
    RadioButton typeBtn;

    DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        userId = firebaseUser.getUid();
        email = firebaseUser.getEmail();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        subjectList = new ArrayList<>();
        levelList = new ArrayList<>();

        spgender = findViewById(R.id.spgender);
        spAdapter = ArrayAdapter.createFromResource(this,R.array.gender,android.R.layout.simple_spinner_item);
        spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spgender.setAdapter(spAdapter);
        spgender.setOnItemSelectedListener(this);

        typeGroup = findViewById(R.id.typeGroup);



        etname = (EditText) findViewById(R.id.etName);
        etaddress =(EditText) findViewById(R.id.etAddress);
        etphone = (EditText) findViewById(R.id.etPhone);
        etinstituition = (EditText) findViewById(R.id.etSchool);
        etdepartment = (EditText) findViewById(R.id.etDepartment);
        etresult = (EditText) findViewById(R.id.etResult);
        etskills = (EditText) findViewById(R.id.etSkills);
        etsalary = (EditText) findViewById(R.id.etSalary);
        ethour = (EditText) findViewById(R.id.etHour);
        etvacancy = (EditText) findViewById(R.id.etVacancy);
        etnid = (EditText) findViewById(R.id.etNID);
        etage = (EditText) findViewById(R.id.etAge);

    }

    public void saveToFirebase(View view) {
        password = "passwd";
        username = etname.getText().toString();
        age = Integer.parseInt(etage.getText().toString());
        institution = etinstituition.getText().toString();
        department = etdepartment.getText().toString();
        result = etresult.getText().toString();
        address = etaddress.getText().toString();
        phone = etphone.getText().toString();
        picture = "Chobi nai";
        skills = etskills.getText().toString();
        salary = etsalary.getText().toString();
        hour = ethour.getText().toString();
        vacancy = Integer.parseInt(etvacancy.getText().toString());

        writeToDatabase();
        Toast.makeText(getBaseContext(),username,Toast.LENGTH_SHORT).show();
    }
    public void writeToDatabase() {
        if (username!=null)
            databaseReference.child(userId).child("name").setValue(username);
        if (age!=0)
            databaseReference.child(userId).child("age").setValue(age);
        if (institution!=null)
            databaseReference.child(userId).child("institution").setValue(institution);
        if (email!=null)
            databaseReference.child(userId).child("email").setValue(email);
        if (department!=null)
            databaseReference.child(userId).child("department").setValue(department);
        if (result!=null)
            databaseReference.child(userId).child("result").setValue(result);
        if (address!=null)
            databaseReference.child(userId).child("address").setValue(address);
        if (phone!=null)
            databaseReference.child(userId).child("phone").setValue(phone);
        if (skills!=null)
            databaseReference.child(userId).child("skills").setValue(skills);
        if (hour!=null)
            databaseReference.child(userId).child("hour").setValue(hour);
        if (gender!=null)
            databaseReference.child(userId).child("gender").setValue(gender);
        if (type!=null)
            databaseReference.child(userId).child("type").setValue(type);
        if (salary!=null)
            databaseReference.child(userId).child("salary").setValue(salary);

        if (!subjectList.isEmpty())
        databaseReference.child(userId).child("subjects").setValue(subjectList);
        if (!levelList.isEmpty())
            databaseReference.child(userId).child("level").setValue(levelList);
    }

    public void selectSubject(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()){
            case R.id.checkBox29:{
                if (checked){
                    subjectList.add("Math");break;
                }
                else {
                    subjectList.remove("Math");break;
                }
            }
            case R.id.checkBox30:{
                if (checked){
                    subjectList.add("Science");break;
                }
                else {
                    subjectList.remove("Science");break;
                }
            }
            case R.id.checkBox31:{
                if (checked){
                    subjectList.add("English");break;
                }
                else {
                    subjectList.remove("English");break;
                }
            }
            case R.id.checkBox32:{
                if (checked){
                    subjectList.add("Bangla");break;
                }
                else {
                    subjectList.remove("Bangla");break;
                }
            }
            case R.id.checkBox33:{
                if (checked){
                    subjectList.add("ICT");break;
                }
                else {
                    subjectList.remove("ICT");break;
                }
            }
            case R.id.checkBox34:{
                if (checked){
                    subjectList.add("Physics");break;
                }
                else {
                    subjectList.remove("Physics");break;
                }
            }
            case R.id.checkBox35:{
                if (checked){
                    subjectList.add("Chemistry");break;
                }
                else {
                    subjectList.remove("Chemistry");break;
                }
            }
            case R.id.checkBox36:{
                if (checked){
                    subjectList.add("Biology");break;
                }
                else {
                    subjectList.remove("Biology");break;
                }
            }
            case R.id.checkBox37:{
                if (checked){
                    subjectList.add("Socail");break;
                }
                else {
                    subjectList.remove("Social");break;
                }
            }
            case R.id.checkBox38:{
                if (checked){
                    subjectList.add("Islam");break;
                }
                else {
                    subjectList.remove("Islam");break;
                }
            }
            case R.id.checkBox39:{
                if (checked){
                    subjectList.add("Hindi");break;
                }
                else {
                    subjectList.remove("Hindi");break;
                }
            }
            case R.id.checkBox40:{
                if (checked){
                    subjectList.add("H. Math");break;
                }
                else {
                    subjectList.remove("H. Math");break;
                }
            }
        }
        //Toast.makeText(this,"Checked/Unchecked",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        gender = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        gender = "null";
    }

    public void selectLevel(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()){
            case R.id.checkBox1:{
                if (checked){
                    levelList.add("Class 1");break;
                }
                else {
                    levelList.remove("Class 1");break;
                }
            }
            case R.id.checkBox2:{
                if (checked){
                    levelList.add("Class 2");break;
                }
                else {
                    levelList.remove("Class 2");break;
                }
            }
            case R.id.checkBox3:{
                if (checked){
                    levelList.add("Class 3");break;
                }
                else {
                    levelList.remove("Class 3");break;
                }
            }
            case R.id.checkBox4:{
                if (checked){
                    levelList.add("Class 4");break;
                }
                else {
                    levelList.remove("Class 4");break;
                }
            }
            case R.id.checkBox5:{
                if (checked){
                    levelList.add("Class 5");break;
                }
                else {
                    levelList.remove("Class 5");break;
                }
            }
            case R.id.checkBox6:{
                if (checked){
                    levelList.add("Class 6");break;
                }
                else {
                    levelList.remove("Class 6");break;
                }
            }
            case R.id.checkBox7:{
                if (checked){
                    levelList.add("Class 7");break;
                }
                else {
                    levelList.remove("Class 7");break;
                }
            }
            case R.id.checkBox8:{
                if (checked){
                    levelList.add("Class 8");break;
                }
                else {
                    levelList.remove("Class 8");break;
                }
            }
            case R.id.checkBox9:{
                if (checked){
                    levelList.add("Class 9");break;
                }
                else {
                    levelList.remove("Class 9");break;
                }
            }
            case R.id.checkBox10:{
                if (checked){
                    levelList.add("Class 10");break;
                }
                else {
                    levelList.remove("Class 10");break;
                }
            }
            case R.id.checkBox11:{
                if (checked){
                    levelList.add("Class 11");break;
                }
                else {
                    levelList.remove("Class 11");break;
                }
            }
            case R.id.checkBox12:{
                if (checked){
                    levelList.add("Class 12");break;
                }
                else {
                    levelList.remove("Class 12");break;
                }
            }
            case R.id.checkBox13:{
                if (checked){
                    levelList.add("Std 1");break;
                }
                else {
                    levelList.remove("Std 1");break;
                }
            }
            case R.id.checkBox14:{
                if (checked){
                    levelList.add("Std 2");break;
                }
                else {
                    levelList.remove("Std 2");break;
                }
            }
            case R.id.checkBox15:{
                if (checked){
                    levelList.add("Std 3");break;
                }
                else {
                    levelList.remove("Std 3");break;
                }
            }
            case R.id.checkBox16:{
                if (checked){
                    levelList.add("Std 4");break;
                }
                else {
                    levelList.remove("Std 4");break;
                }
            }
            case R.id.checkBox17:{
                if (checked){
                    levelList.add("Std 5");break;
                }
                else {
                    levelList.remove("Std 5");break;
                }
            }
            case R.id.checkBox18:{
                if (checked){
                    levelList.add("Std 6");break;
                }
                else {
                    levelList.remove("Std 6");break;
                }
            }
            case R.id.checkBox19:{
                if (checked){
                    levelList.add("Std 7");break;
                }
                else {
                    levelList.remove("Std 7");break;
                }
            }
            case R.id.checkBox20:{
                if (checked){
                    levelList.add("Std 8");break;
                }
                else {
                    levelList.remove("Std 8");break;
                }
            }
            case R.id.checkBox21:{
                if (checked){
                    levelList.add("Std 9");break;
                }
                else {
                    levelList.remove("Std 9");break;
                }
            }
            case R.id.checkBox22:{
                if (checked){
                    levelList.add("Std 10");break;
                }
                else {
                    levelList.remove("Std 10");break;
                }
            }
            case R.id.checkBox23:{
                if (checked){
                    levelList.add("Std 11");break;
                }
                else {
                    levelList.remove("Std 11");break;
                }
            }
            case R.id.checkBox24:{
                if (checked){
                    levelList.add("Std 12");break;
                }
                else {
                    levelList.remove("Std 12");break;
                }
            }
            case R.id.checkBox25:{
                if (checked){
                    levelList.add("IELTS");break;
                }
                else {
                    levelList.remove("IELTS");break;
                }
            }
            case R.id.checkBox26:{
                if (checked){
                    levelList.add("Spoken");break;
                }
                else {
                    levelList.remove("Spoken");break;
                }
            }
            case R.id.checkBox27:{
                if (checked){
                    levelList.add("GRE");break;
                }
                else {
                    levelList.remove("GRE");break;
                }
            }
            case R.id.checkBox28:{
                if (checked){
                    levelList.add("GMAT");break;
                }
                else {
                    levelList.remove("GMAT");break;
                }
            }
        }
        //Toast.makeText(this,":-)",Toast.LENGTH_SHORT).show();
    }

    public void checkType(View view) {
        int radioId = typeGroup.getCheckedRadioButtonId();
        typeBtn = findViewById(radioId);
        Toast.makeText(this,"I am "+typeBtn.getText().toString(),Toast.LENGTH_SHORT).show();
        type = typeBtn.getText().toString();
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

}
