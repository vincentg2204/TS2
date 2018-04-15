package com.example.windows10.ts2;

class Route {
    private String display_name;
    private String id;

    public Route(String display_name, String id) {
        this.display_name = display_name;
        this.id = id;
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
