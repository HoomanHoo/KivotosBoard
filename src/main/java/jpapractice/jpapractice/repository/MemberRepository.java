package jpapractice.jpapractice.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.transaction.Transactional;
import jpapractice.jpapractice.domain.Account;
import jpapractice.jpapractice.domain.Student;

@Repository
public class MemberRepository {

    private final EntityManager em;

    @Autowired
    public MemberRepository(EntityManager em) {
        this.em = em;
    }

    public Student saveStudent(Student student) {

        em.persist(student);

        return student;
    }

    public Account saveAccount(Account account) {

        em.persist(account);

        return account;

    }

    public Optional<Account> accountFindById(String id) {
        Account result = em.find(Account.class, id);
        // System.out.println(result);
        return Optional.ofNullable(result);
    }
}
