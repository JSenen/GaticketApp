package com.juansenen.gaticketapp.domain;

import java.time.LocalDateTime;

public class Messages {

    private long idMenssage;
    private String messageCommit;
    private LocalDateTime timeMessage;
    private Incidences incidenciaMessage;
    private User emisorMessage; // Puede ser Usuario o Administrador

    public long getIdMenssage() {
        return idMenssage;
    }

    public void setIdMenssage(long idMenssage) {
        this.idMenssage = idMenssage;
    }

    public String getMessageCommit() {
        return messageCommit;
    }

    public void setMessageCommit(String messageCommit) {
        this.messageCommit = messageCommit;
    }

    public LocalDateTime getTimeMessage() {
        return timeMessage;
    }

    public void setTimeMessage(LocalDateTime timeMessage) {
        this.timeMessage = timeMessage;
    }

    public Incidences getIncidenciaMessage() {
        return incidenciaMessage;
    }

    public void setIncidenciaMessage(Incidences incidenciaMessage) {
        this.incidenciaMessage = incidenciaMessage;
    }

    public User getEmisorMessage() {
        return emisorMessage;
    }

    public void setEmisorMessage(User emisorMessage) {
        this.emisorMessage = emisorMessage;
    }
}
