// package jpapractice.jpapractice.repository;

// import java.util.List;
// import java.util.Optional;
// import org.springframework.stereotype.Repository;

// import jakarta.persistence.EntityManager;
// import jpapractice.jpapractice.domain.Account;

// @Repository
// public class AccountRepository {
// private final EntityManager em;

// public AccountRepository(EntityManager em) {
// this.em = em;
// }

// public Account saveAccount(Account account) {
// em.persist(account);
// // em.flush();
// return account;

// }

// public Optional<Account> findAccountById(String id) {
// Account result = em.find(Account.class, id);
// return Optional.ofNullable(result);
// }

// public Optional<Account> getReferenceAccount(String id) {
// Account result = em.getReference(Account.class, id);
// return Optional.ofNullable(result);
// }

// }
