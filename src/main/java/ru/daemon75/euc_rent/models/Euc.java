package ru.daemon75.euc_rent.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Euc {
    private int id;
    private Integer userId;
    @NotEmpty(message = "Model should not be empty")
    @Size(min = 2, max = 128, message = "model-name should be from 2 to 128 symbols")
    private String model;
    @Size(min = 2, max = 128, message = "serial number should be from 2 to 128 symbols")
    private String serialN;
    @Min(value = 1, message = "weight should be more 1")
    private int weight;
    @Min(value = 1, message = "distance should be more 1")
    private int distance;
    @NotEmpty(message = "Tyre should not be empty")
    @Size(min = 2, max = 128, message = "Tyre should be from 2 to 128 symbols")
    private String tyre;
    @Min(value = 1, message = "price should be more 1")
    private int dayPrice;
    @Min(value = 1, message = "deposit should be more 1")
    private int deposit;

    public Euc() {
    }

    public Euc(int id, int userId, String model, String serialN, int weight, int distance, String tyre, int dayPrice, int deposit) {
        this.id = id;
        this.userId = userId;
        this.model = model;
        this.serialN = serialN;
        this.weight = weight;
        this.distance = distance;
        this.tyre = tyre;
        this.dayPrice = dayPrice;
        this.deposit = deposit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialN() {
        return serialN;
    }

    public void setSerialN(String serialN) {
        this.serialN = serialN;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getTyre() {
        return tyre;
    }

    public void setTyre(String tyre) {
        this.tyre = tyre;
    }

    public int getDayPrice() {
        return dayPrice;
    }

    public void setDayPrice(int dayPrice) {
        this.dayPrice = dayPrice;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }
}
