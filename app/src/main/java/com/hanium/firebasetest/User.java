package com.hanium.firebasetest;

public class User {
    private String time;
    private String Volt;
    private String Resistor;
    private String Current;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVolt() {
        return Volt;
    }

    public void setVolt(String volt) {
        Volt = volt;
    }

    public String getResistor() {
        return Resistor;
    }

    public void setResistor(String resistor) {
        Resistor = resistor;
    }

    public String getCurrent() {
        return Current;
    }

    public void setCurrent(String current) {
        Current = current;
    }

    public User(){}

}
