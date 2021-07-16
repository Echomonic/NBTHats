package dev.echo.hats.utils;

public enum ConfigTypes {

    permission("permissions"),
    error("error"),
    message("message"),
    options("options"),
    ;

    private String type;


    ConfigTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
