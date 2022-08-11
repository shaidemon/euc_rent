package ru.daemon75.euc_rent.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.daemon75.euc_rent.models.User;

import java.util.List;

@Component
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(User user) {
        jdbcTemplate.update("INSERT INTO users(login_email, password, fullname, birth_year, address, agreement, deposit) " +
                        "VALUES (?,?,?,?,?,?,?)", user.getLoginEmail(), user.getPassword(), user.getFullname(),
                user.getBirthYear(), user.getAddress(), user.getAgreement(), user.getDeposit());
    }

    public List<User> getAll() {
        return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));
    }

    public User getById(int id) {
        return jdbcTemplate.query("SELECT * FROM users WHERE id = ?", new BeanPropertyRowMapper<>(User.class), id)
                .stream().findAny().orElse(null);
    }

    public User getByLoginEmail(String login) {
        return jdbcTemplate.query("SELECT * FROM users WHERE login_email = ?", new BeanPropertyRowMapper<>(User.class), login)
                .stream().findAny().orElse(null);
    }

    public List<User> getByFullName(String fullName) {
        return jdbcTemplate.query("SELECT * FROM users WHERE fullname = ?", new BeanPropertyRowMapper<>(User.class), fullName);
    }

    public void update(int id, User u) {
        jdbcTemplate.update("UPDATE users SET login_email = ?, password = ?, fullname = ?, birth_year = ?, address = ?" +
                        ", agreement = ?, deposit = ?, enabled = ? WHERE id = ?", u.getLoginEmail(), u.getPassword(), u.getFullname()
                , u.getBirthYear(), u.getAddress(), u.getAgreement(), u.getDeposit(), u.isEnabled(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
    }
}
