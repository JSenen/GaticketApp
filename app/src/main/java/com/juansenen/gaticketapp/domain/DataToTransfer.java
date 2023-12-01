package com.juansenen.gaticketapp.domain;

/**
 * Clase para intercambiar datos en masa entre Activitys
 */
public class DataToTransfer {

    private static DataToTransfer instance;

    private String theme;
    private String commit;
    private String solution;
    private String date;
    private String user;

    private DataToTransfer(){};

    public static synchronized DataToTransfer getInstance() {
        if (instance == null) {
            instance = new DataToTransfer();
        }
        return instance;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getCommit() {
        return commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
