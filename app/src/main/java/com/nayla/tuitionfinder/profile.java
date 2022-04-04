package com.nayla.tuitionfinder;

import java.util.ArrayList;
import java.util.List;

public class profile {
    private String name;
    private String email;
    private String address;
    private String institution;
    private String department;
    List<String> subjectList;
    List<String> levelList;
    private String salary;
    private String phone;
    private String photo;
    private String type;
    private String UID;

    public profile() {
    }

    public profile(String name, String email, String address, String institution, String department, String salary, String phone, String photo, List<String> level, List<String> subjects,String type) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.institution = institution;
        this.department = department;
        this.salary = salary;
        this.phone = phone;
        this.photo = photo;
        this.levelList = level;
        this.subjectList = subjects;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLevel() {
        return levelList.toString();
    }

    public void setLevel(List<String> level) {
        this.levelList = level;
    }

    public String getSubjects() {
        return subjectList.toString();
    }

    public void setSubjects(List<String> subjects) {
        this.subjectList = subjects;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }
}
