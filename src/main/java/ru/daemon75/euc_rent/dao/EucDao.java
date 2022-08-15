package ru.daemon75.euc_rent.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.daemon75.euc_rent.models.Euc;

import java.util.List;

@Component
public class EucDao {
    private final JdbcTemplate jdbcTemplate;
    private static final RowMapper<Euc> EUC_ROW_MAPPER = BeanPropertyRowMapper.newInstance(Euc.class);

    @Autowired
    public EucDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Euc euc) {
        jdbcTemplate.update("INSERT INTO eucs (model, serial_n, weight, distance, tyre, day_price, deposit) " +
                        "VALUES (?,?,?,?,?,?,?)", euc.getModel(), euc.getSerialN(), euc.getWeight(), euc.getDistance(),
                euc.getTyre(), euc.getDayPrice(), euc.getDeposit());
    }

    public List<Euc> getAll() {
        return jdbcTemplate.query("SELECT * FROM eucs", new BeanPropertyRowMapper<>(Euc.class));
    }

    public Euc getById (int id) {
        return jdbcTemplate.query("SELECT * FROM eucs WHERE id = ?", EUC_ROW_MAPPER, id).stream().findAny().orElse(null);
    }

    public List<Euc> getByUserId (int userId) {
        return jdbcTemplate.query("SELECT * FROM eucs WHERE user_id = ?", EUC_ROW_MAPPER, userId);
    }

    public Euc getBySerialN (String serialN) {
        return jdbcTemplate.query("SELECT * FROM eucs WHERE serial_n = ?", EUC_ROW_MAPPER, serialN)
                .stream().findAny().orElse(null);
    }

    public void update (int id, Euc euc) {
        jdbcTemplate.update("UPDATE eucs SET model = ?, serial_n = ?, weight = ?, distance = ?, tyre = ?, " +
                "day_price = ?, deposit = ? WHERE id = ?", euc.getModel(), euc.getSerialN(), euc.getWeight(),
                euc.getDistance(), euc.getTyre(), euc.getDayPrice(), euc.getDeposit(), id);
    }

    public void toRent (int id, Integer userId) {
        jdbcTemplate.update("UPDATE eucs SET user_id = ? WHERE id = ?", userId, id);
    }

    public void free(int id) {
        jdbcTemplate.update("UPDATE eucs SET user_id = ? WHERE id = ?", null, id);
    }

    public void delete (int id) {
        jdbcTemplate.update("DELETE FROM eucs WHERE id = ?", id);
    }
}
