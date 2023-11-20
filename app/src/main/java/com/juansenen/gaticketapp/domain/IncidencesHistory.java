package com.juansenen.gaticketapp.domain;

import java.util.Date;

public class IncidencesHistory {


    private long idhistory;
    private String historyTip;
    private String historyTheme;
    private String historyCommit;
    private Date historyDateFinish;
    private String historyAdmin;
    private String historySolution;

    public long getIdhistory() {
        return idhistory;
    }

    public void setIdhistory(long idhistory) {
        this.idhistory = idhistory;
    }

    public String getHistoryTip() {
        return historyTip;
    }

    public void setHistoryTip(String historyTip) {
        this.historyTip = historyTip;
    }

    public String getHistoryTheme() {
        return historyTheme;
    }

    public void setHistoryTheme(String historyTheme) {
        this.historyTheme = historyTheme;
    }

    public String getHistoryCommit() {
        return historyCommit;
    }

    public void setHistoryCommit(String historyCommit) {
        this.historyCommit = historyCommit;
    }

    public Date getHistoryDateFinish() {
        return historyDateFinish;
    }

    public void setHistoryDateFinish(Date historyDateFinish) {
        this.historyDateFinish = historyDateFinish;
    }

    public String getHistoryAdmin() {
        return historyAdmin;
    }

    public void setHistoryAdmin(String historyAdmin) {
        this.historyAdmin = historyAdmin;
    }

    public String getHistorySolution() {
        return historySolution;
    }

    public void setHistorySolution(String historySolution) {
        this.historySolution = historySolution;
    }
}
