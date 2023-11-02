package jpapractice.jpapractice.repository;

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
        return student;
    }

    // public
}
