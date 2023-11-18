package com.juansenen.gaticketapp.domain;

import java.util.Date;
import java.util.List;

public class Incidences {

    private long incidencesId;
    private String incidenceCommit;
    private String incidenceStatus;
    private String incidenceDate;
    private String incidenceDateFinish;
    private String incidenceTheme;
    private Integer adminId;
    private Device device;
    private User user; //Clave de la tabla principal
    private User responsable;
    private List<Messages> messagesList;

    public Incidences() {

    }

    public long getIncidencesId() {
        return incidencesId;
    }

    public void setIncidencesId(long incidencesId) {
        this.incidencesId = incidencesId;
    }

    public String getIncidenceCommit() {
        return incidenceCommit;
    }

    public void setIncidenceCommit(String incidenceCommit) {
        this.incidenceCommit = incidenceCommit;
    }

    public String getIncidenceStatus() {
        return incidenceStatus;
    }

    public void setIncidenceStatus(String incidenceStatus) {
        this.incidenceStatus = incidenceStatus;
    }

    public String getIncidenceDate() {
        return incidenceDate;
    }

    public void setIncidenceDate(String incidenceDate) {
        this.incidenceDate = incidenceDate;
    }

    public String getIncidenceDateFinish() {
        return incidenceDateFinish;
    }

    public void setIncidenceDateFinish(String incidenceDateFinish) {
        this.incidenceDateFinish = incidenceDateFinish;
    }

    public String getIncidenceTheme() {
        return incidenceTheme;
    }

    public void setIncidenceTheme(String incidenceTheme) {
        this.incidenceTheme = incidenceTheme;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getResponsable() {
        return responsable;
    }

    public void setResponsable(User responsable) {
        this.responsable = responsable;
    }

    public List<Messages> getMessagesList() {
        return messagesList;
    }

    public void setMessagesList(List<Messages> messagesList) {
        this.messagesList = messagesList;
    }
}
