package jpapractice.jpapractice.repository;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jpapractice.jpapractice.domain.Account;

@Repository
public class AccountRepository {
    private final EntityManager em;

    public AccountRepository(EntityManager em) {
        this.em = em;
    }

    public Account save(Account account) {
        em.persist(account);
        return account;
    }
}
