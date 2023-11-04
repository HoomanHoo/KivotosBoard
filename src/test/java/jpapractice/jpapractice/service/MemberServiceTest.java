package jpapractice.jpapractice.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jpapractice.jpapractice.domain.Account;
import jpapractice.jpapractice.dto.DefaultInfoDto;
import jpapractice.jpapractice.dto.StudentAndAccountDto;
import jpapractice.jpapractice.repository.AccountRepository;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    AccountRepository accountRepository;

    @Transactional
    @Test
    public void joinTest() {
        StudentAndAccountDto dto = new StudentAndAccountDto();

        dto.setSchoolId(1);
        dto.setClubId(1);
        dto.setPositionId(1);
        dto.setStudentType("BACK");
        dto.setName("김철수");
        dto.setAge(19);
        dto.setEmail("testemail@email.com");
        dto.setAccountId("testaccount");
        dto.setAccountPasswd("test1234");

        memberService.join(dto);
        DefaultInfoDto result = memberService.findInfo(dto.getAccountId());

        // System.out.println(result.toString());

    }

    @Test
    public void AccountTest() {
        Account account = accountRepository.findById("test111").get();

        Assertions.assertThat(account.getId()).isEqualTo("test111");
    }

}
