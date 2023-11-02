package jpapractice.jpapractice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpapractice.jpapractice.domain.Account;
import jpapractice.jpapractice.domain.Student;
import jpapractice.jpapractice.dto.StudentAndAccountDTO;
import jpapractice.jpapractice.repository.AccountRepository;
import jpapractice.jpapractice.repository.InformationRepository;
import jpapractice.jpapractice.repository.MemberRepository;

@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final AccountRepository accountRepository;
    private final InformationRepository informationRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository, AccountRepository accountRepository,
            InformationRepository informationRepository) {
        this.memberRepository = memberRepository;
        this.accountRepository = accountRepository;
        this.informationRepository = informationRepository;
    }

    public StudentAndAccountDTO join(StudentAndAccountDTO studentAndAccountDto) {

        Student student = Student.builder()
                .name(studentAndAccountDto.getName())
                .age(studentAndAccountDto.getAge())
                .email(studentAndAccountDto.getEmail())
                .school(informationRepository.schoolFindById(studentAndAccountDto.getSchoolId()))
                .club(informationRepository.clubFindById(studentAndAccountDto.getClubId()))
                .position(informationRepository.clubPositionFindById(studentAndAccountDto.getPositionId()))
                .type(studentAndAccountDto.getStudentType())
                .build();

        Account account = Account.builder()
                .id(studentAndAccountDto.getAccountId())
                .passwd(studentAndAccountDto.getAccountPasswd())
                .student(student)
                .build();

        memberRepository.save(student);
        accountRepository.save(account);

        studentAndAccountDto.setName(student.getName());
        studentAndAccountDto.setAge(student.getAge());
        studentAndAccountDto.setEmail(student.getEmail());
        studentAndAccountDto.setSchoolId(student.getSchool().getId().intValue());
        studentAndAccountDto.setClubId(student.getClub().getId().intValue());
        studentAndAccountDto.setPositionId(student.getPosition().getId().intValue());
        studentAndAccountDto.setAccountId(account.getId());
        studentAndAccountDto.setAccountPasswd(account.getPasswd());

        return studentAndAccountDto;
    }

}
