package com.example.cv;

public class CV {
    private int id;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String education;
    private String skills;
    private String experience;
    private String projects;

    public CV(int id, String fullName, String email, String phone, String address,
              String education, String skills, String experience, String projects) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.education = education;
        this.skills = skills;
        this.experience = experience;
        this.projects = projects;
    }

    public int getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public String getEducation() { return education; }
    public String getSkills() { return skills; }
    public String getExperience() { return experience; }
    public String getProjects() { return projects; }

    @Override
    public String toString() {
        return fullName + " (" + email + ")";
    }
}