package jpapractice.jpapractice.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jpapractice.jpapractice.domain.StudentType;
import jpapractice.jpapractice.dto.StudentAndAccountDTO;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    public void joinTest() {
        StudentAndAccountDTO dto = new StudentAndAccountDTO();

        dto.setSchoolId(1);
        dto.setClubId(1);
        dto.setPositionId(1);
        dto.setStudentType("BACK");
        dto.setName("카가미 치히로");
        dto.setAge(19);
        dto.setEmail("testemail@email.com");
        dto.setAccountId("testaccount");
        dto.setAccountPasswd("test1234");

        StudentAndAccountDTO result = memberService.join(dto);

        dto.toString();
        result.toString();

        Assertions.assertThat(dto).isEqualTo(result);
    }

}
