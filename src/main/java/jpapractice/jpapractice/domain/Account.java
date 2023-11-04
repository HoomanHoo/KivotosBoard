package jpapractice.jpapractice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "member_account_information")
@Getter
public class Account {
    @Id
    @Column(name = "account_id")
    private String id;

    @Column(name = "account_passwd")
    private String passwd;

    @JsonIgnore
    @OneToOne // (mappedBy = "account")
    @JoinColumn(name = "student_id")
    private Student student;

    @Override
    public String toString() {
        return "Account [id=" + id + ", passwd=" + passwd + ", student=" + student + "]";
    }

    public Account() {
    }

    @Builder
    public Account(String id, String passwd, Student student) {
        this.id = id;
        this.passwd = passwd;
        this.student = student;
    }
}
