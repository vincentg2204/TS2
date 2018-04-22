package com.example.windows10.ts2;

class Route {
    private String display_name;
    private String id;
    private String fgColor;
    private String bgColor;

    public Route(String display_name, String id) {
        this.display_name = display_name;
        this.id = id;
        fgColor="#ffffff";
        bgColor="#000000";
    }

    public String getFgColor() {
        return fgColor;
    }

    public void setFgColor(String fgColor) {
        this.fgColor = fgColor;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
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
}
