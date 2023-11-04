package jpapractice.jpapractice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentAndAccountDto {
    private int schoolId;
    private int clubId;
    private int positionId;
    private String studentType;
    private String name;
    private int age;
    private String email;

    private String accountId;
    private String accountPasswd;

    @Override
    public String toString() {
        return "StudentAndAccountDTO [schoolId=" + schoolId + ", clubId=" + clubId + ", positionId=" + positionId
                + ", studentType=" + studentType + ", name=" + name + ", age=" + age + ", email=" + email
                + ", accountId=" + accountId + ", accountPasswd=" + accountPasswd + "]";
    }

}
