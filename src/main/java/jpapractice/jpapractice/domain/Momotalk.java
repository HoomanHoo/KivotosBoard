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
@Table(name = "momotalk_account_information")
@Getter
public class Momotalk {

    @Id
    @Column(name = "momotalk_id")
    private String id;

    @OneToOne // (mappedBy = "momotalkAccount")
    @JoinColumn(name = "student_id")
    private Student student;

    public Momotalk() {
    }

    @Builder
    public Momotalk(String id, Student student) {
        this.id = id;
        this.student = student;
    }

}
