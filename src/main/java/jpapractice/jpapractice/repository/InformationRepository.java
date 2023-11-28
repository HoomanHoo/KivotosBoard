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

    public School getSchoolReference(int schoolId) {
        return em.getReference(School.class, schoolId);
    }

    public Club getClubReference(int clubId) {
        return em.getReference(Club.class, clubId);
    }

    public ClubPosition getClubPositionReference(int clubPositionId){
        return em.getReference(ClubPosition.class, clubPositionId);
    }

}
