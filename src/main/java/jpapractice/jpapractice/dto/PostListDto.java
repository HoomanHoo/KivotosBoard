package jpapractice.jpapractice.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostListDto {
    private Long id;
    private String postSubject;
    private LocalDateTime postDate;
    private String name;

    @Override
    public String toString() {
        return "PostListDto [id=" + id + ", postSubject=" + postSubject + ", postDate=" + postDate + ", name=" + name
                + "]";
    }

}
