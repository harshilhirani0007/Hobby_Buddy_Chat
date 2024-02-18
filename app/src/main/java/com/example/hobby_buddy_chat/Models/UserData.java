package com.example.hobby_buddy_chat.Models;

public class UserData {
    String userId,name,username,email,age,password,gender,profilePicture,bio;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public UserData(String userId, String name, String username, String email, String age, String password, String gender, String profilePicture, String bio) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.email = email;
        this.age = age;
        this.password = password;
        this.gender = gender;
        this.profilePicture = profilePicture;
        this.bio = bio;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public String getBio() {
        return bio;
    }
}
