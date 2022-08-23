package ru.daemon75.euc_rent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.daemon75.euc_rent.models.Euc;

import java.util.List;

@Repository
public interface EucsRepository extends JpaRepository<Euc, Integer> {

    List<Euc> findByUserId(Integer userId);

    Euc findBySerialN(String serialN);
}
