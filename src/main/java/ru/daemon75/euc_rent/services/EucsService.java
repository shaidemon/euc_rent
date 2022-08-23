package ru.daemon75.euc_rent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.daemon75.euc_rent.models.Euc;
import ru.daemon75.euc_rent.models.User;
import ru.daemon75.euc_rent.repositories.EucsRepository;
import ru.daemon75.euc_rent.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class EucsService {
    private final EucsRepository eucsRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public EucsService(EucsRepository eucsRepository, UsersRepository usersRepository) {
        this.eucsRepository = eucsRepository;
        this.usersRepository = usersRepository;
    }

    public List<Euc> getAll() {
        return eucsRepository.findAll();
    }

    public Euc getById(int id) {
        Optional<Euc> euc = eucsRepository.findById(id);
        return euc.orElse(null);
    }

    public List<Euc> getByUserId(Integer userId) {
        return eucsRepository.findByUserId(userId);
    }

    public Euc getBySerialN(String serialN) {
        return eucsRepository.findBySerialN(serialN);
    }

    @Transactional
    public void save(Euc euc) {
        eucsRepository.save(euc);
    }

    @Transactional
    public void update(int id, Euc euc) {
        euc.setId(id);
        eucsRepository.save(euc);
    }

    @Transactional
    public void toRent(int id, User user) {
        Euc eucToRent = getById(id);

        eucToRent.setUser(user);
        eucsRepository.save(eucToRent);
    }

    @Transactional
    public void free(int id) {
        Euc eucToRent = getById(id);
        eucToRent.setUser(null);
        eucsRepository.save(eucToRent);
    }

    @Transactional
    public void delete(int id) {
        eucsRepository.deleteById(id);
    }
}
