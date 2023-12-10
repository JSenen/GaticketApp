package com.juansenen.gaticketapp.domain;

import java.util.List;

public class Department {

    private long departemtId;
    private String departmentName;
    private String departmentPhone;
    private String departmentMail;
    private String departmentCity;

    private List<Device> devices;
    private List<User> userList;

    public long getDepartemtId() {
        return departemtId;
    }

    public void setDepartemtId(long departemtId) {
        this.departemtId = departemtId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentPhone() {
        return departmentPhone;
    }

    public void setDepartmentPhone(String departmentPhone) {
        this.departmentPhone = departmentPhone;
    }

    public String getDepartmentMail() {
        return departmentMail;
    }

    public void setDepartmentMail(String departmentMail) {
        this.departmentMail = departmentMail;
    }

    public String getDepartmentCity() {
        return departmentCity;
    }

    public void setDepartmentCity(String departmentCity) {
        this.departmentCity = departmentCity;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
