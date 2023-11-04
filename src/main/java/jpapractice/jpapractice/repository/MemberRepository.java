package jpapractice.jpapractice.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jpapractice.jpapractice.domain.Student;

@Repository
public class MemberRepository {

    private final EntityManager em;

    @Autowired
    public MemberRepository(EntityManager em) {
        this.em = em;
    }

    public Student save(Student student) {
        em.persist(student);
        em.flush();
        return student;
        // em.close();
    }

    public Optional<Student> findById(int id) {
        Student result = em.find(Student.class, id);
        return Optional.ofNullable(result);
    }

    public Optional<Student> findByName(String name) {
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

}
