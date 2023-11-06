package jpapractice.jpapractice.service;

import java.time.LocalTime;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jpapractice.jpapractice.domain.Account;
import jpapractice.jpapractice.domain.Student;
import jpapractice.jpapractice.dto.DefaultInfoDto;
import jpapractice.jpapractice.dto.StudentAndAccountDto;
import jpapractice.jpapractice.repository.MemberRepository;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    EntityManager em;

    @Autowired
    private MemberRepository memberRepository;

    // @Transactional
    @Test
    public void joinTest() {
        System.out.println("joinTest 시작");
        em.clear();
        StudentAndAccountDto dto = new StudentAndAccountDto();

        dto.setSchoolId(1);
        dto.setClubId(1);
        dto.setPositionId(1);
        dto.setStudentType("BACK");
        dto.setName("김철수");
        dto.setAge(19);
        dto.setEmail("testemail@email.com");
        dto.setAccountId("test1112");
        dto.setAccountPasswd("test1234");

        memberService.join(dto);

        DefaultInfoDto result = memberService.findInfo(dto.getAccountId());
        System.out.println("result: " + result.toString());

        em.flush();
        em.clear();

        System.out.println("joinTest 끝");

    }

    // @Transactional
    // @Test
    // public void AccountTest() {

    // System.out.println("AccountTest 시작");

    // em.clear();

    // Long start1 = System.currentTimeMillis();

    // Account account = memberRepository.findStudentById("mirror111").get();

    // DefaultInfoDto dto = new DefaultInfoDto();

    // dto.setStudentName(account.getStudent().getName());
    // dto.setSchoolName(account.getStudent().getSchool().getSchoolName());
    // dto.setClubName(account.getStudent().getClub().getName());
    // dto.setPositionName(account.getStudent().getPosition().getName());
    // dto.setStudentType(account.getStudent().getType().name());

    // System.out.println("dto1: " + dto.toString());

    // Long end1 = System.currentTimeMillis();

    // // Assertions.assertThat(account.getId()).isEqualTo("mirror111");

    // em.flush();
    // em.clear();

    // System.out.println("직접 만든 쿼리 실행 시간: " + (end1 - start1));

    // System.out.println("AccountTest 끝");
    // }

    @Test
    public void findStudentInfoByAccountIdTest() {
        System.out.println("findStudentInfoByAccountIdTest 시작");

        em.clear();

        Long start2 = System.currentTimeMillis();

        Account account2 = memberRepository.findAccountById("mirror111").get();

        DefaultInfoDto dto2 = new DefaultInfoDto();

        dto2.setStudentName(account2.getStudent().getName());
        dto2.setSchoolName(account2.getStudent().getSchool().getSchoolName());
        dto2.setClubName(account2.getStudent().getClub().getName());
        dto2.setPositionName(account2.getStudent().getPosition().getName());
        dto2.setStudentType(account2.getStudent().getType().name());

        System.out.println("dto2: " + dto2.toString());

        Long end2 = System.currentTimeMillis();

        em.flush();
        em.clear();

        System.out.println("find 메서드: " + (end2 - start2));

        System.out.println("findStudentInfoByAccountIdTest 끝");

    }

}
