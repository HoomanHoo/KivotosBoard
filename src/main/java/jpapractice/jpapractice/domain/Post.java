package jpapractice.jpapractice.domain;

import java.util.ArrayList;
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

import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private long id;

    @Column(name = "post_subject")
    private String postSubject;

    @Column(name = "post_post")
    private String postContent;

    @Column(name = "post_date")
    private LocalDateTime postDate;

    @Column(name = "post_view")
    private long view;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    public Post() {
    }

    @Builder
    public Post(long id, String subject, String postContent, LocalDateTime postDate, long view, Student student,
            List<Comment> comments) {
        this.id = id;
        this.postSubject = subject;
        this.postContent = postContent;
        this.postDate = postDate;
        this.view = view;
        this.student = student;
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Post [id=" + id + ", postSubject=" + postSubject + ", postContent=" + postContent + ", postDate="
                + postDate + ", view=" + view + ", student=" + student + "]";
    }

}
