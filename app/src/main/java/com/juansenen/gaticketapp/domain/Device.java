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
    private Date deviceDateBuy;
    private Date deviceDateStart;
    private Type deviceType;
    private List<Incidences> incidencesList;
    private Department department;
    private Net net; // Relación OneToOne con una red

}
