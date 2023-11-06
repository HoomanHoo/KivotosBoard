package jpapractice.jpapractice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultInfoDto {
    private String studentName;
    private int age;
    private String email;
    private String schoolName;
    private String clubName;
    private String positionName;
    private String studentType;

    @Override
    public String toString() {
        return "DefaultInfoDto [studentName=" + studentName + ", age=" + age + ", email=" + email + ", schoolName="
                + schoolName + ", clubName=" + clubName + ", positionName=" + positionName + ", studentType="
                + studentType + "]";
    }

}
