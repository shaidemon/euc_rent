package ru.daemon75.euc_rent.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "global_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "login should not be empty")
    @Email(message = "login should be in email-format")
    @Column(name = "login_email")
    private String loginEmail;

    @Size(min = 6, max = 128, message = "password should contain at least 6 symbols")
    @Column(name = "password")
    private String password;

    @NotEmpty(message = "Full name should not be empty")
    @Size(min = 2, max = 64, message = "Name should be from 2 to 64 letters")
    @Column(name = "fullname")
    private String fullname;

    @Min(value = 1900, message = "year should be later 1900")
    @Column(name = "birth_year")
    private int birthYear;

    @NotEmpty(message = "address should not be empty")
    @Pattern(regexp = "[A-Z]\\w+(\\h*\\w*)*, [A-Z]\\w+\\h*\\w*, \\d{6}", message = "Address should be this format: Country, City, 6-digits-zipcode")
    @Column(name = "address")
    private String address;

    @NotEmpty(message = "agreement should not be empty")
    @Column(name = "agreement")
    private String agreement;

    @Min(value = 0, message = "deposit should be zero or more")
    @Column(name = "deposit")
    private double deposit;

    @Column(name = "enabled")
    private boolean enabled;

    @OneToMany(mappedBy = "user")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    public List<Euc> eucs;

    public User() {
    }

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

    public List<Euc> getEucs() {
        return eucs;
    }

    public void setEucs(List<Euc> eucs) {
        this.eucs = eucs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() && getLoginEmail().equals(user.getLoginEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLoginEmail());
    }
}
