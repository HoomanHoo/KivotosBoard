package jpapractice.jpapractice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WritePostDto {
    @NotNull(message = "제목을 입력해주세요")
    private String subject;
    @NotNull(message = "내용을 입력해주세요")
    private String content;

    private String writer;

}
