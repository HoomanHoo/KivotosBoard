package jpapractice.jpapractice.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "student_default_information")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    @Column(name = "student_name")
    private String name;

    @Column(name = "student_age")
    private int age;

    @Column(name = "student_email")
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    private ClubPosition position;

    @Enumerated(EnumType.STRING)
    private StudentType type;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private List<Post> posts;

    @OneToOne(mappedBy = "student")
    private Account account;

    @OneToOne(mappedBy = "student")
    private Momotalk momotalkAccount;

    @Override
    public String toString() {
        return "student_id: " + this.id + "student_name: " + this.name + "student_age: " + this.age
                + "student_email: " + this.email + "student_type: " + this.type;
    }

    public Student() {
    }

    @Builder
    public Student(Long id, String name, int age, String email, School school, Club club, ClubPosition position,
            String type, List<Post> posts, Account account, Momotalk momotalkAccount) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.school = school;
        this.club = club;
        this.position = position;
        this.posts = posts;
        this.account = account;
        this.momotalkAccount = momotalkAccount;
        if (type.equals("BACK")) {
            this.type = StudentType.BACK;
        } else if (type.equals("MIDDLE")) {
            this.type = StudentType.MIDDLE;
        } else if (type.equals("FRONT")) {
            this.type = StudentType.FRONT;
        }

    }

}
