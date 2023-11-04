package jpapractice.jpapractice.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

import jpapractice.jpapractice.domain.Account;
import jpapractice.jpapractice.domain.Student;

@SpringBootTest

public class memberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    InformationRepository informationRepository;

    @Transactional
    @Test
    public void findByNameWithAccountTest() {
        Student student = Student.builder().name("김휘집").age(23).email("zipzip@zip.com")
                .school(informationRepository.getReferenceSchool(1)).club(informationRepository.getReferenceClub(1))
                .position(informationRepository.getReferenceClubPosition(1)).type("FRONT").build();
        Account account = Account.builder().id("zipziptest").passwd("1111").build();

        memberRepository.save(student);
        accountRepository.save(account);

        System.out.println("테스트1 끝");

        // Optional<Student> optionalResult =
        // memberRepository.findByNameWithAccount(student.getName());
        // if (optionalResult.isPresent()) {
        // Student result = optionalResult.get();
        // System.out.println(result.toString());
        // } else {
        // System.out.println("업다 객체가");
        // }
    }

    @Transactional
    @Test
    public void findTest() {

        Account result = accountRepository.findById("zipziptest").get();
        // Optional<Student> nameResult = memberRepository.findByName("김휘집");
        // if (nameResult.isPresent()) {
        // Student result = nameResult.get();

        // if (result.getAccount() != null) {
        // System.out.println(result.getAccount());
        // } else {
        // System.out.println("업댄다");
        // }
        // } else {
        // System.out.println("업다 이름으로 선택한 객체가");
        // }
    }

}
