package jpapractice.jpapractice.dto.member;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultInfoDto {

  private String id;
  private String studentName;
  private int age;
  private String email;
  private String schoolName;
  private String clubName;
  private String positionName;
  private String studentType;

  @Override
  public String toString() {
    return "DefaultInfoDto [id=" + id + ", studentName=" + studentName
        + ", age=" + age + ", email=" + email
        + ", schoolName=" + schoolName + ", clubName=" + clubName
        + ", positionName=" + positionName
        + ", studentType=" + studentType + "]";
  }

  @Builder
  public DefaultInfoDto(String id, String studentName, int age,
      String email, String schoolName, String clubName,
      String positionName, String studentType) {
    this.id = id;
    this.studentName = studentName;
    this.age = age;
    this.email = email;
    this.schoolName = schoolName;
    this.clubName = clubName;
    this.positionName = positionName;
    this.studentType = studentType;
  }

  public DefaultInfoDto() {
  }

}
