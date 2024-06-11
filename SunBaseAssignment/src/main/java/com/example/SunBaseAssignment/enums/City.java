package com.example.SunBaseAssignment.enums;

public enum City {
    NEW_YORK("New York"),
    LOS_ANGELES("Los Angeles"),
    CHICAGO("Chicago"),
    HOUSTON("Houston"),
    PHILADELPHIA("Philadelphia"),
    DELHI("Delhi");

    private final String cityName;

    City(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }
}
