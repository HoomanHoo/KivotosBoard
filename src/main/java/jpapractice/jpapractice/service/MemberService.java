package jpapractice.jpapractice.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import jpapractice.jpapractice.customException.DataNotFoundException;
import jpapractice.jpapractice.domain.Account;
import jpapractice.jpapractice.domain.Student;
import jpapractice.jpapractice.dto.DefaultInfoDto;
import jpapractice.jpapractice.dto.StudentAndAccountDto;

import jpapractice.jpapractice.repository.InformationRepository;
import jpapractice.jpapractice.repository.MemberRepository;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final InformationRepository informationRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberService(MemberRepository memberRepository, InformationRepository informationRepository,
            PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.informationRepository = informationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public String registMember(StudentAndAccountDto studentAndAccountDto) {
        Student studentInfo = new Student()
                .builder()
                .name(studentAndAccountDto.getName())
                .age(studentAndAccountDto.getAge())
                .email(studentAndAccountDto.getEmail())
                .school(informationRepository.getSchoolReference(studentAndAccountDto.getSchoolId()))
                .club(informationRepository.getClubReference(studentAndAccountDto.getClubId()))
                .position(informationRepository.getClubPositionReference(studentAndAccountDto.getPositionId()))
                .type(studentAndAccountDto.getStudentType())
                .build();

        Student student = memberRepository.saveStudent(studentInfo);
        Account accountInfo = new Account()
                .builder()
                .id(studentAndAccountDto.getAccountId())
                .passwd(passwordEncoder.encode(studentAndAccountDto.getAccountPasswd()))
                .student(student)
                .build();

        Account account = memberRepository.saveAccount(accountInfo);

        return account.getId();
    }

    public DefaultInfoDto findInfo(String account) {
        Optional<Account> result = memberRepository.accountFindById(account);
        if (!result.isPresent()) {
            throw new DataNotFoundException("account not found");
        }
        return new DefaultInfoDto();

    }

}