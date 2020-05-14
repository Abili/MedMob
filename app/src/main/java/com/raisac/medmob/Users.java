package com.raisac.medmob;

public class Users {
    public String fName;
    public String lName;
    public String age;
    public String profile_pics;
    public String mDateOfBirth;
    public String mDepartment;
    public String mExperience;

    public Users() {
    }


    public Users(String name/*, String photoUrl, String lastName, String Age,
                 String dateOfBirth, String department, String experience*/) {
        this.fName = name;
//        this.profile_pics = photoUrl;
//        this.mDateOfBirth = dateOfBirth;
//        this.mDepartment = department;
//        this.mExperience = experience;
    }

    public String getDateOfBirth() {
        return mDateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        mDateOfBirth = dateOfBirth;
    }

    public String getDepartment() {
        return mDepartment;
    }

    public void setDepartment(String department) {
        mDepartment = department;
    }

    public String getExperience() {
        return mExperience;
    }

    public void setExperience(String experience) {
        mExperience = experience;
    }

    public String getlName() {
        return lName;
    }


    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getProfile_pics() {
        return profile_pics;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }


    public void setProfile_pics(String profile_pics) {
        this.profile_pics = profile_pics;
    }
}
