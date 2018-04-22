package com.example.windows10.ts2;

public class MainPresenter {
    private MainActivity ui;
    private Agency[] agency;
    private Route[] routes;
    private Stop[] stops;
    private Agency cAgency;
    private Route cRoute;

    public MainPresenter(MainActivity ui){
        this.ui = ui;
    }
    public void initAgency(int size){
        agency = new Agency[size];
    }
    public void setAgency(Agency[] agency){
        this.agency = agency;
    }

    public Agency[] getAgency() {
        return agency;
    }

    public Route[] getRoutes() {
        return routes;
    }

    public void setRoutes(Route[] routes) {
        this.routes = routes;
    }

    public Stop[] getStops() {
        return stops;
    }

    public void setStops(Stop[] stops) {
        this.stops = stops;
        updateStop();
    }
    public void updateStop(){
        ui.updateStop();
    }
    public Agency getcAgency() {
        return cAgency;
    }

    public void setcAgency(Agency cAgency) {
        this.cAgency = cAgency;
    }

    public Route getcRoute() {
        return cRoute;
    }

    public void setcRoute(Route cRoute) {
        this.cRoute = cRoute;
    }
}
