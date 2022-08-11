package ru.daemon75.euc_rent.models;

public class User {
    private int id;
    private String loginEmail;
    private String password;
    private String fullname;
    private int birthYear;
    private String address;
    private String agreement;
    private double deposit;
    private boolean enabled;

    public User () {}

    public User(int id, String loginEmail, String password, String fullname, int birthYear, String address,
                String agreement, double deposit, boolean enabled) {
        this.id = id;
        this.loginEmail = loginEmail;
        this.password = password;
        this.fullname = fullname;
        this.birthYear = birthYear;
        this.address = address;
        this.agreement = agreement;
        this.deposit = deposit;
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginEmail() {
        return loginEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAgreement() {
        return agreement;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
