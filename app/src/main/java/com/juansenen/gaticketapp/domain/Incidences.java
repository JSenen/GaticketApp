package com.juansenen.gaticketapp.domain;

import java.util.Date;
import java.util.List;

public class Incidences {

    private long incidencesId;
    private String incidenceCommit;
    private String incidenceStatus;
    private Date incidenceDate;
    private Date incidenceDateFinish;
    private String incidenceTheme;
    private Integer adminId;
    private Device device;
    private User user; //Clave de la tabla principal
    private User responsable;
    private List<Messages> messagesList;
}
