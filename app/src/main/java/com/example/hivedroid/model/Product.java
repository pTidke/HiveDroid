package com.example.hivedroid.model;

public class Product {

    private String nameProduct;
    private float goalAmount;
    private float raisedAmount;
    private String startDate;
    private String endDate;
    private String description;
    private String idCreator;

    public Product() {
    }

    public Product(String nameProduct, float goalAmount, float raisedAmount, String startDate, String endDate, String description, String idCreator) {
        this.nameProduct = nameProduct;
        this.goalAmount = goalAmount;
        this.raisedAmount = raisedAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.idCreator = idCreator;
    }

    public void setRaisedAmount(float raisedAmount) {
        this.raisedAmount = raisedAmount;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public float getGoalAmount() {
        return goalAmount;
    }

    public float getRaisedAmount() {
        return raisedAmount;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }

    public String getIdCreator() {
        return idCreator;
    }
}
