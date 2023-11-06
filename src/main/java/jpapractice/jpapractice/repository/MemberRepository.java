package jpapractice.jpapractice.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
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
        // em.flush();
        return student;
    }

    public Account saveAccount(Account account) {
        em.persist(account);
        // em.flush();
        return account;

    }

    // public Optional<Account> findStudentById(String id) {
    // Account result = em
    // .createQuery(
    // "select a"
    // + " from Account a"
    // + " join fetch a.student s"
    // + " join fetch a.student.school sc"
    // + " join fetch a.student.club c"
    // + " join fetch a.student.position p"
    // + " where a.id= :id",
    // Account.class)
    // .setParameter("id", id).getSingleResult();

    // return Optional.ofNullable(result);
    // }
    // find 메서드보다 더 느려서 폐기함

    public Optional<Account> findAccountById(String id) {
        Account result = em.find(Account.class, id);
        return Optional.ofNullable(result);
    }

    public Optional<Student> findStudentByName(String name) {
        List<Student> result = em
                .createQuery("select m from Student m where m.name= :name", Student.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    public Optional<Student> findByNameWithAccount(String name) {
        List<Student> result = em
                .createQuery("select s from Student s join fetch s.account a where s.name= :name", Student.class)
                .setParameter("name", name)
                .getResultList();
        System.out.println("결과 리스트 길이: " + result.size());

        return result.stream().findAny();
    }

    public Optional<Account> getReferenceAccount(String id) {
        Account result = em.getReference(Account.class, id);
        return Optional.ofNullable(result);
    }

}
