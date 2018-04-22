package com.example.windows10.ts2;

public class Prediction {
    private double minutes;
    private String block_id;
    private String run_id;
    private String route_id;
    private double seconds;
    private boolean is_departing;

    public Prediction(){
        minutes = 0;
        block_id = "";
        route_id = "";
        run_id = "";
        seconds = 0;
        is_departing = false;
    }

    public Prediction(double minutes, String block_id, String run_id, String route_id, double seconds, boolean is_departing) {
        this.minutes = minutes;
        this.block_id = block_id;
        this.run_id = run_id;
        this.route_id = route_id;
        this.seconds = seconds;
        this.is_departing = is_departing;
    }

    public double getMinutes() {
        return minutes;
    }

    public void setMinutes(double minutes) {
        this.minutes = minutes;
    }

    public String getBlock_id() {
        return block_id;
    }

    public void setBlock_id(String block_id) {
        this.block_id = block_id;
    }

    public String getRun_id() {
        return run_id;
    }

    public void setRun_id(String run_id) {
        this.run_id = run_id;
    }

    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }

    public double getSeconds() {
        return seconds;
    }

    public void setSeconds(double seconds) {
        this.seconds = seconds;
    }

    public boolean isIs_departing() {
        return is_departing;
    }

    public void setIs_departing(boolean is_departing) {
        this.is_departing = is_departing;
    }
}
