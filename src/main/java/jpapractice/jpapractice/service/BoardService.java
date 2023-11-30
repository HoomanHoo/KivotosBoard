package jpapractice.jpapractice.service;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jpapractice.jpapractice.domain.Comment;
import jpapractice.jpapractice.domain.Post;
import jpapractice.jpapractice.domain.Student;
import jpapractice.jpapractice.dto.CommentDto;
import jpapractice.jpapractice.dto.PostDto;
import jpapractice.jpapractice.dto.PostListDto;
import jpapractice.jpapractice.dto.WritePostDto;
import jpapractice.jpapractice.repository.BoardRepository;
import jpapractice.jpapractice.repository.InformationRepository;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final InformationRepository informationRepository;

    public BoardService(BoardRepository boardRepository, InformationRepository informationRepository) {
        this.boardRepository = boardRepository;
        this.informationRepository = informationRepository;
    }

    @Transactional
    public Long createPost(WritePostDto writePostDto) {
        Student student = informationRepository.getAccountById(writePostDto.getWriter()).getStudent();
        Post post = new Post().builder()
                .subject(writePostDto.getSubject())
                .postContent(writePostDto.getContent())
                .student(student)
                .postDate(LocalDateTime.now())
                .view(0)
                .build();

        return boardRepository.savePost(post).getId();
    }

    @Transactional
    public List<PostListDto> loadPosts(int pageNum) {
        int startPost = pageNum - 1;
        return boardRepository.findPosts(startPost);
    }

    @Transactional
    public PostDto getPost(Long postId) {
        Post post = boardRepository.getPost(postId);
        List<Comment> comments = post.getComments();
        if (comments.isEmpty()) {
            PostDto postDto = new PostDto();
            postDto.setPostId(post.getId());
            postDto.setPostSubject(post.getPostSubject());
            postDto.setPostContent(post.getPostContent());
            postDto.setPostDate(post.getPostDate());
            postDto.setView(post.getView());
            postDto.setStudentName(post.getStudent().getName());

            return postDto;
        } else {
            List<CommentDto> commentDtos = new ArrayList<>();
            for (int i = 0; i < comments.size(); i++) {
                Comment comment = comments.get(i);
                CommentDto commentDto = new CommentDto();
                commentDto.setCommentId(comment.getId());
                commentDto.setCommentText(comment.getCommentText());
                commentDto.setStudentName(comment.getStudent().getName());
                commentDto.setCommentDate(comment.getCommentDate());
                commentDtos.add(commentDto);
            }
            PostDto postDto = new PostDto();
            postDto.setPostId(post.getId());
            postDto.setPostSubject(post.getPostSubject());
            postDto.setPostContent(post.getPostContent());
            postDto.setPostDate(post.getPostDate());
            postDto.setView(post.getView());
            postDto.setStudentName(post.getStudent().getName());
            postDto.setComments(commentDtos);
            return postDto;
        }

    }
}
