package com.example.windows10.ts2;

class Stop {
    private String display_name;
    private String id;
    private double longitude;
    private double latitude;
    private double waktu = -1;

    public Stop(String display_name, String id, double longitude, double latitude) {
        this.display_name = display_name;
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getWaktu() {
        return waktu;
    }

    public void setWaktu(double waktu) {
        this.waktu = waktu;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
