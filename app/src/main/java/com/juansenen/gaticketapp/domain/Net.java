package com.juansenen.gaticketapp.domain;

public class Net {

    private long netId;
    private String netIp;
    private String netMask;
    private String netCdir;
    private String netGateWay;
    private boolean netStatus;

    public Net(long netId, String netIp, String netMask, String netCdir, String netGateWay, boolean netStatus) {
        this.netId = netId;
        this.netIp = netIp;
        this.netMask = netMask;
        this.netCdir = netCdir;
        this.netGateWay = netGateWay;
        this.netStatus = netStatus;
    }

    public long getNetId() {
        return netId;
    }

    public void setNetId(long netId) {
        this.netId = netId;
    }

    public String getNetIp() {
        return netIp;
    }

    public void setNetIp(String netIp) {
        this.netIp = netIp;
    }

    public String getNetMask() {
        return netMask;
    }

    public void setNetMask(String netMask) {
        this.netMask = netMask;
    }

    public String getNetCdir() {
        return netCdir;
    }

    public void setNetCdir(String netCdir) {
        this.netCdir = netCdir;
    }

    public String getNetGateWay() {
        return netGateWay;
    }

    public void setNetGateWay(String netGateWay) {
        this.netGateWay = netGateWay;
    }

    public boolean isNetStatus() {
        return netStatus;
    }

    public void setNetStatus(boolean netStatus) {
        this.netStatus = netStatus;
    }
}
