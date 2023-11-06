package jpapractice.jpapractice.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityExistsException;
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

    @Autowired
    public MemberService(MemberRepository memberRepository, InformationRepository informationRepository) {
        this.memberRepository = memberRepository;
        this.informationRepository = informationRepository;
    }

    @Transactional
    public void join(StudentAndAccountDto studentAndAccountDto) {

        System.out.println("join 메서드 시작");
        Student student = Student.builder()
                .name(studentAndAccountDto.getName())
                .age(studentAndAccountDto.getAge())
                .email(studentAndAccountDto.getEmail())
                .school(informationRepository.getReferenceSchool(studentAndAccountDto.getSchoolId()))
                .club(informationRepository.getReferenceClub(studentAndAccountDto.getClubId()))
                .position(informationRepository.getReferenceClubPosition(studentAndAccountDto.getPositionId()))
                .type(studentAndAccountDto.getStudentType())
                .build();

        Account account = Account.builder()
                .id(studentAndAccountDto.getAccountId())
                .passwd(studentAndAccountDto.getAccountPasswd())
                .student(student)
                .build();

        try {
            Student studentResult = memberRepository.saveStudent(student);
            Account accountResult = memberRepository.saveAccount(account);

            if (studentResult.getAccount() == null) {
                System.out.println("아이고 객체에 계정값이 없네");
            } else {
                System.out.println("계정 값이 있네?");
            }

            studentResult.addAccountInfo(accountResult);
            Student studentResult2 = memberRepository.saveStudent(studentResult);

            if (studentResult2.getAccount() == null) {
                System.out.println("아이고 객체에 계정값이 없네");
            } else {
                System.out.println("계정 값이 있네?");
            }

        } catch (EntityExistsException e) {
            e.getStackTrace();
        } finally {
            System.out.println("join 메서드 끝");
        }

    }

    // student.addAccountInfo(account); 저장하지 않은 객체를 집어넣고 저장하려 해서 에러남
    // org.hibernate.TransientPropertyValueException: object references an unsaved
    // transient instance - save the transient instance before flushing :
    // jpapractice.jpapractice.domain.Student.account ->
    // jpapractice.jpapractice.domain.Account

    @Transactional
    public DefaultInfoDto findInfo(String id) {
        System.out.println("findInfo 메서드 시작");

        DefaultInfoDto defaultInfoDto = new DefaultInfoDto();

        Optional<Account> accountOptional = memberRepository.findAccountById(id);
        if (!accountOptional.isPresent()) {

        } else {
            Account accountResult = accountOptional.get();
            defaultInfoDto.setStudentName(accountResult.getStudent().getName());
            defaultInfoDto.setAge(accountResult.getStudent().getAge());
            defaultInfoDto.setEmail(accountResult.getStudent().getEmail());
            defaultInfoDto.setSchoolName(accountResult.getStudent().getSchool().getSchoolName());
            defaultInfoDto.setClubName(accountResult.getStudent().getClub().getName());
            defaultInfoDto.setPositionName(accountResult.getStudent().getPosition().getName());
            defaultInfoDto.setStudentType(accountResult.getStudent().getType().name());
        }
        // System.out.println("Service: " + studentResult.toString());
        // System.out.println("Service: " + studentResult.getAccount().toString());

        // System.out.println(defaultInfoDto.toString());
        System.out.println("findInfo 메서드 끝");
        return defaultInfoDto;
    }

}
