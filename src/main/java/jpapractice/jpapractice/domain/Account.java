package jpapractice.jpapractice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Override
    public String toString() {
        return "account_id: " + this.id + "account_passwd: " + this.passwd;
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