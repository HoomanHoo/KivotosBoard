package jpapractice.jpapractice.domain;

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

  // @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "school_id")
  private School school;

  // @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "club_id")
  private Club club;

  // @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "position_id")
  private ClubPosition position;

  @Enumerated(EnumType.STRING)
  private StudentType type;

  public void changeAge(int age) {
    this.age = age;
  }

  public void changeEmail(String email) {
    this.email = email;
  }

  public void changeClub(Club club) {
    this.club = club;
  }

  public void changeClubPosition(ClubPosition position) {
    this.position = position;
  }

  public void changeStudentType(int type) {
    if (type == 3) {
      this.type = StudentType.BACK;
    } else if (type == 2) {
      this.type = StudentType.MIDDLE;
    } else if (type == 1) {
      this.type = StudentType.FRONT;
    }
  }

  @Override
  public String toString() {
    return "Student [id=" + id + ", name=" + name + ", age=" + age
        + ", email=" + email + "]";
  }

  public Student() {
  }

  @Builder
  public Student(Long id, String name, int age, String email,
      School school, Club club, ClubPosition position,
      int type) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.email = email;
    this.school = school;
    this.club = club;
    this.position = position;
    if (type == 3) {
      this.type = StudentType.BACK;
    } else if (type == 2) {
      this.type = StudentType.MIDDLE;
    } else if (type == 1) {
      this.type = StudentType.FRONT;
    }

  }

  // @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
  // private List<Post> posts = new ArrayList<>();

  // @OneToOne(mappedBy = "student", fetch = FetchType.LAZY)
  // private Account account;

  // @OneToOne(mappedBy = "student", fetch = FetchType.LAZY)
  // private Momotalk momotalkAccount;

  // @Override
  // public String toString() {
  // return "Student [id=" + id + ", name=" + name + ", age=" + age + ", email=" +
  // email + ", school=" + school
  // + ", club=" + club + ", position=" + position + ", type=" + type + ", posts="
  // + posts + ", account="
  // + account + ", momotalkAccount=" + momotalkAccount + "]";
  // }
  // toString 메서드를 오버라이딩 해서 필드 전체를 리턴시키면 객체타입 필드들에 의해서 select 문 연산이 시작된다.


}
