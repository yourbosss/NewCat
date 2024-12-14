package com.example.newcat2.data.model;

public class FilterRequest {
    private int is_adult;
    private int is_premium;
    private int is_sterilized;
    private int is_wet;

    public FilterRequest(int isAdult, int isPremium, int isSterilized, int isWet) {
        this.is_adult = isAdult;
        this.is_premium = isPremium;
        this.is_sterilized = isSterilized;
        this.is_wet = isWet;
    }

    // Геттеры и сеттеры
    public int getIsAdult() {
        return is_adult;
    }

    public void setIsAdult(int isAdult) {
        this.is_adult = isAdult;
    }

    public int getIsPremium() {
        return is_premium;
    }

    public void setIsPremium(int isPremium) {
        this.is_premium = isPremium;
    }

    public int getIsSterilized() {
        return is_sterilized;
    }

    public void setIsSterilized(int isSterilized) {
        this.is_sterilized = isSterilized;
    }

    public int getIsWet() {
        return is_wet;
    }

    public void setIsWet(int isWet) {
        this.is_wet = isWet;
    }
}