package com.aceware.cobrandprepaidkit;

public class SingleToneClass {
    public String s;
    private static final SingleToneClass ourInstance = new SingleToneClass();
    public static SingleToneClass getInstance() {
        return ourInstance;
    }
    private SingleToneClass() {
    }
    public void setData(String s) {
        this.s = s;
    }
    public String getData() {
        return s;
    }
}
