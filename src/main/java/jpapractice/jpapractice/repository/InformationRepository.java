package jpapractice.jpapractice.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jpapractice.jpapractice.domain.Club;
import jpapractice.jpapractice.domain.ClubPosition;
import jpapractice.jpapractice.domain.School;

@Repository
public class InformationRepository {

    private final EntityManager em;

    @Autowired
    public InformationRepository(EntityManager em) {
        this.em = em;
    }

    public School schoolFindById(int id) {
        School school = em.find(School.class, id);
        return school;
    }

    public Club clubFindById(int id) {
        Club club = em.find(Club.class, id);
        return club;
    }

    public ClubPosition clubPositionFindById(int id) {
        ClubPosition clubPosition = em.find(ClubPosition.class, id);
        return clubPosition;
    }

}
