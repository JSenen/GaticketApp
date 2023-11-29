package com.juansenen.gaticketapp.domain;

import java.util.Date;

public class IncidencesHistory {


    private long idhistory;
    private String historyTip;
    private String historyTheme;
    private String historyCommit;
    private String historyDateFinish;
    private String historyAdmin;
    private String historySolution;

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

    public String getHistoryDateFinish() {
        return historyDateFinish;
    }

    public void setHistoryDateFinish(String historyDateFinish) {
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
