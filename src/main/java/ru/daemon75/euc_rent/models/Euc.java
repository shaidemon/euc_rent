package ru.daemon75.euc_rent.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "eucs")
public class Euc {
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "global_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @NotEmpty(message = "Model should not be empty")
    @Size(min = 2, max = 128, message = "model-name should be from 2 to 128 symbols")
    @Column(name = "model")
    private String model;

    @Size(min = 2, max = 128, message = "serial number should be from 2 to 128 symbols")
    @Column(name = "serial_N")
    private String serialN;

    @Min(value = 1, message = "weight should be more 1")
    @Column(name = "weight")
    private int weight;

    @Min(value = 1, message = "distance should be more 1")
    @Column(name = "distance")
    private int distance;

    @NotEmpty(message = "Tyre should not be empty")
    @Size(min = 2, max = 128, message = "Tyre should be from 2 to 128 symbols")
    @Column(name = "tyre")
    private String tyre;

    @Min(value = 1, message = "price should be more 1")
    @Column(name = "day_price")
    private int dayPrice;

    @Min(value = 1, message = "deposit should be more 1")
    @Column(name = "deposit")
    private int deposit;

    @Min(value = 1, message = "speed must be more 1")
    @Column(name = "speed")
    private Integer speed;

    @Column(name = "rent_start")
    private Timestamp rentStart;

    @Column(name = "rent_days")
    private Integer rentDays;

    public Euc() {
    }

    public Euc(int id, String model, String serialN, int weight, int distance, String tyre, int dayPrice, int deposit, int speed) {
        this.id = id;
        this.model = model;
        this.serialN = serialN;
        this.weight = weight;
        this.distance = distance;
        this.tyre = tyre;
        this.dayPrice = dayPrice;
        this.deposit = deposit;
        this.speed = speed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Timestamp getRentStart() {
        return rentStart;
    }

    public void setRentStart(Timestamp rentStart) {
        this.rentStart = rentStart;
    }

    public Integer getRentDays() {
        return rentDays;
    }

    public void setRentDays(Integer rentDays) {
        this.rentDays = rentDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Euc)) return false;
        Euc euc = (Euc) o;
        return getId() == euc.getId() && getModel().equals(euc.getModel()) && getSerialN().equals(euc.getSerialN());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getModel(), getSerialN());
    }
}
