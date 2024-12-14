package com.example.newcat2.data.model;

public class FoodItem {
    private int id;
    private String name;
    private String description;
    private int quantity;
    private int booked;
    private int colorie_content;
    private boolean sterilized;
    private boolean adult;
    private boolean wet;
    private boolean domestic;
    private int price;

    // Геттеры и сеттеры
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getQuantity() { return quantity; }
    public int getBooked() { return booked; }
    public int getColorieContent() { return colorie_content; }
    public boolean isSterilized() { return sterilized; }
    public boolean isAdult() { return adult; }
    public boolean isWet() { return wet; }
    public boolean isDomestic() { return domestic; }
    public int getPrice() { return price; }
}
