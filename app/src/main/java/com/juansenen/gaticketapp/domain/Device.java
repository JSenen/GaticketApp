package com.juansenen.gaticketapp.domain;

import java.util.Date;
import java.util.List;

public class Device {

    private long deviceId;
    private int deviceHd;
    private int deviceRam;
    private String deviceMac;
    private String deviceSerial;
    private String deviceModel;
    private String deviceDateBuy;
    private String deviceDateStart;
    private Type deviceType;
    private List<Incidences> incidencesList;
    private Department department;
    private Net net; // Relación OneToOne con una red

    public Device(long deviceId, int deviceHd, int deviceRam, String deviceMac, String deviceSerial, String deviceModel, String deviceDateBuy, String deviceDateStart, Type deviceType, List<Incidences> incidencesList, Department department, Net net) {
        this.deviceId = deviceId;
        this.deviceHd = deviceHd;
        this.deviceRam = deviceRam;
        this.deviceMac = deviceMac;
        this.deviceSerial = deviceSerial;
        this.deviceModel = deviceModel;
        this.deviceDateBuy = deviceDateBuy;
        this.deviceDateStart = deviceDateStart;
        this.deviceType = deviceType;
        this.incidencesList = incidencesList;
        this.department = department;
        this.net = net;
    }

    public Device() {

    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public int getDeviceHd() {
        return deviceHd;
    }

    public void setDeviceHd(int deviceHd) {
        this.deviceHd = deviceHd;
    }

    public int getDeviceRam() {
        return deviceRam;
    }

    public void setDeviceRam(int deviceRam) {
        this.deviceRam = deviceRam;
    }

    public String getDeviceMac() {
        return deviceMac;
    }

    public void setDeviceMac(String deviceMac) {
        this.deviceMac = deviceMac;
    }

    public String getDeviceSerial() {
        return deviceSerial;
    }

    public void setDeviceSerial(String deviceSerial) {
        this.deviceSerial = deviceSerial;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceDateBuy() {
        return deviceDateBuy;
    }

    public void setDeviceDateBuy(String deviceDateBuy) {
        this.deviceDateBuy = deviceDateBuy;
    }

    public String getDeviceDateStart() {
        return deviceDateStart;
    }

    public void setDeviceDateStart(String deviceDateStart) {
        this.deviceDateStart = deviceDateStart;
    }

    public Type getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Type deviceType) {
        this.deviceType = deviceType;
    }

    public List<Incidences> getIncidencesList() {
        return incidencesList;
    }

    public void setIncidencesList(List<Incidences> incidencesList) {
        this.incidencesList = incidencesList;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Net getNet() {
        return net;
    }

    public void setNet(Net net) {
        this.net = net;
    }
}
