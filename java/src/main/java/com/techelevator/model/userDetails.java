package com.techelevator.model;

public class userDetails {

//    user_id INT NOT NULL,
//    email_address VARCHAR(50),
//    is_supervisor BOOLEAN NOT NULL,
//    department_id int NOT NULL,
    private int userId;
    private String emailAddress;
    private boolean isSupervisor;
    private int departmentId;

    public userDetails (){}
    public userDetails (int userId, String emailAddress, boolean isSupervisor, int departmentId){
        this.userId = userId;
        this.emailAddress = emailAddress;
        this.isSupervisor = isSupervisor;
        this.departmentId = departmentId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean getIsSupervisor() {
        return isSupervisor;
    }

    public void setIsSupervisor(boolean supervisor) {
        isSupervisor = supervisor;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
