package jpapractice.jpapractice.domain;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "club_position")
public class ClubPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    private Long id;

    @Column(name = "position_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

    @OneToMany(mappedBy = "position", fetch = FetchType.LAZY)
    private List<Student> student;

    public ClubPosition() {
    }

    @Builder
    public ClubPosition(Long id, String name, Club club, List<Student> student) {
        this.id = id;
        this.name = name;
        this.club = club;
        this.student = student;
    }

}
