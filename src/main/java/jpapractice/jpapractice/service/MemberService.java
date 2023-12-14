package jpapractice.jpapractice.service;

import jakarta.transaction.Transactional;
import java.util.Optional;
import jpapractice.jpapractice.customException.DataNotFoundException;
import jpapractice.jpapractice.domain.Account;
import jpapractice.jpapractice.domain.Student;
import jpapractice.jpapractice.dto.member.DefaultInfoDto;
import jpapractice.jpapractice.dto.member.StudentAndAccountDto;
import jpapractice.jpapractice.repository.InformationRepository;
import jpapractice.jpapractice.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

  private final MemberRepository memberRepository;
  private final InformationRepository informationRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public MemberService(MemberRepository memberRepository,
      InformationRepository informationRepository,
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
        .school(informationRepository.getSchoolReference(
            studentAndAccountDto.getSchoolId()))
        .club(informationRepository.getClubReference(
            studentAndAccountDto.getClubId()))
        .position(informationRepository.getClubPositionReference(
            studentAndAccountDto.getPositionId()))
        .type(studentAndAccountDto.getStudentType())
        .build();

    Student student = memberRepository.saveStudent(studentInfo);
    Account accountInfo = new Account()
        .builder()
        .id(studentAndAccountDto.getAccountId())
        .passwd(passwordEncoder.encode(
            studentAndAccountDto.getAccountPasswd()))
        .student(student)
        .build();

    Account account = memberRepository.saveAccount(accountInfo);

    return account.getId();
  }

  public DefaultInfoDto findInfo(String accountId) {
    Optional<Account> result = memberRepository.accountFindById(accountId);
    if (result.isEmpty()) {
      throw new DataNotFoundException("account not found");
    }
    Account account = result.get();
    DefaultInfoDto defaultInfoDto
        = DefaultInfoDto.builder()
                        .id(account.getId())
                        .studentName(
                            account.getStudent()
                                   .getName())
                        .age(account.getStudent()
                                    .getAge())
                        .email(
                            account.getStudent()
                                   .getEmail())
                        .schoolName(
                            account.getStudent()
                                   .getSchool()
                                   .getSchoolName())
                        .clubName(
                            account.getStudent()
                                   .getClub()
                                   .getName())
                        .positionName(
                            account.getStudent()
                                   .getPosition()
                                   .getName())
                        .studentType(
                            account.getStudent()
                                   .getType()
                                   .name())
                        .build();
    return defaultInfoDto;

  }

  @Transactional
  public StudentAndAccountDto getUserInfoForModify(String accountId) {
    Optional<Account> optionalAccount = memberRepository.accountFindById(
        accountId);
    if (optionalAccount.isEmpty()) {
      throw new DataNotFoundException("사용자가 존재하지 않습니다.");
    }
    Account account = optionalAccount.get();
    StudentAndAccountDto studentAndAccountDto = new StudentAndAccountDto();
    studentAndAccountDto.setAccountId(account.getId());
    studentAndAccountDto.setEmail(account.getStudent().getEmail());
    studentAndAccountDto.setAge(account.getStudent().getAge());
    studentAndAccountDto.setName(account.getStudent().getName());
    studentAndAccountDto.setSchoolId(
        account.getStudent().getSchool().getId().intValue());
    studentAndAccountDto.setClubId(
        account.getStudent().getClub().getId().intValue());
    studentAndAccountDto.setPositionId(
        account.getStudent().getPosition().getId().intValue());
    String type = account.getStudent().getType().name();
    if (type.equals("BACK")) {
      studentAndAccountDto.setStudentType(3);
    } else if (type.equals("MIDDLE")) {
      studentAndAccountDto.setStudentType(2);
    } else if (type.equals("FRONT")) {
      studentAndAccountDto.setStudentType(1);
    }
    return studentAndAccountDto;

  }

  @Transactional
  public String modifyInfo(StudentAndAccountDto studentAndAccountDto,
      String accountId) {
    Optional<Account> optionalAccount = memberRepository.accountFindById(
        accountId);
    if (optionalAccount.isEmpty()) {
      throw new DataNotFoundException("해당하는 사용자가 없습니다.");
    } else {
      Account account = optionalAccount.get();
      Student student = account.getStudent();
      account.changePasswd(
          passwordEncoder.encode(studentAndAccountDto.getAccountPasswd()));
      student.changeAge(studentAndAccountDto.getAge());
      student.changeEmail(studentAndAccountDto.getEmail());
      student.changeClub(informationRepository.getClubReference(
          studentAndAccountDto.getClubId()));
      student.changeClubPosition(
          informationRepository.getClubPositionReference(
              studentAndAccountDto.getPositionId()));
      student.changeStudentType(studentAndAccountDto.getStudentType());
      return accountId;
    }
  }

  @Transactional
  public String getPassword(String accountId) {
    Account account = memberRepository.accountFindById(accountId)
                                      .orElseThrow(
                                          () -> new DataNotFoundException(
                                              "사용자가 존재하지 않습니다"));
    return account.getPasswd();
  }

  @Transactional
  public void unregistMember(String accountId) {
    Account account = informationRepository.getAccountReference(accountId);
    memberRepository.unregistMember(account);
  }

}