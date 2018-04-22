package com.example.windows10.ts2;

public class Color {
    private String fg_color;
    private String id;
    private String bg_color;
    private String display_name;

    public Color(String fg_color, String id, String bg_color, String display_name) {
        this.fg_color = fg_color;
        this.id = id;
        this.bg_color = bg_color;
        this.display_name = display_name;
    }

    public String getFg_color() {
        return fg_color;
    }

    public void setFg_color(String fg_color) {
        this.fg_color = fg_color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBg_color() {
        return bg_color;
    }

    public void setBg_color(String bg_color) {
        this.bg_color = bg_color;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }
}
