package jpapractice.jpapractice.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "school_info", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "school_name" }, name = "school_name_unique") }) // unique 제약 조건을 학교명 컬럼에 준다
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_id")
    private Long id;

    @Column(name = "school_name") // 학교 이름은 중복되어선 안된다
    private String schoolName;
    // @Column 어노테이션 옵션으로 unique = true를 줄 수 있으나 constraint 이름이 난수로 생성되기 때문에
    // @Table 어노테이션의 uniqueConstraints 옵션으로 제약을 줄 컬럼과 이름을 지정해준다

    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Club> clubList;
    // School은 여러개의 Club을 가질 수 있는 1대다 관계이다
    // 반대로 말하면 Club은 여러개가 하나의 School을 가진다
    // 그렇기 때문에 Club 엔티티에는 ManyToOne, School 엔티티는 OneToMany를 가진다
    // CascadeType.REMOVE는 학교가 삭제되면 연관된 동아리도 모두 지운다는 의미이다
    // School은 여러개의 Club을 가질 수 있기 때문에 List 타입으로 해당 클럽들을 불러올 수 있도록 해야한다

    @OneToMany(mappedBy = "school")
    private List<Student> student;

    public School() {
    }

    @Builder
    public School(Long id, String schoolName, List<Club> clubList, List<Student> student) {
        this.id = id;
        this.schoolName = schoolName;
        this.clubList = clubList;
        this.student = student;
    }

}
