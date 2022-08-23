package ru.daemon75.euc_rent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.daemon75.euc_rent.models.User;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {

    User findByLoginEmail(String loginEmail);

    List<User> findUsersByFullnameContainingIgnoreCase(String fullname);
}
