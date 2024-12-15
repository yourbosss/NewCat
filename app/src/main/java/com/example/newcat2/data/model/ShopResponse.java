package com.example.newcat2.data.model;

import java.util.List;

public class ShopResponse {
    private List<Shop> data;

    public List<Shop> getData() {
        return data;
    }

    public void setData(List<Shop> data) {
        this.data = data;
    }

    public static class Shop {
        private int id;
        private String name;
        private String description;
        private List<Integer> feed_ids;
        private double latitude;
        private double longitude;

        // Getters and Setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<Integer> getFeedIds() {
            return feed_ids;
        }

        public void setFeedIds(List<Integer> feed_ids) {
            this.feed_ids = feed_ids;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }
}