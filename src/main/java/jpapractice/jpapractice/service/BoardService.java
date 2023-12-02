package jpapractice.jpapractice.service;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jpapractice.jpapractice.domain.Account;
import jpapractice.jpapractice.domain.Comment;
import jpapractice.jpapractice.domain.Post;
import jpapractice.jpapractice.domain.Student;
import jpapractice.jpapractice.dto.CommentDto;
import jpapractice.jpapractice.dto.PostDto;
import jpapractice.jpapractice.dto.PostListDto;
import jpapractice.jpapractice.dto.WritePostDto;
import jpapractice.jpapractice.repository.BoardRepository;
import jpapractice.jpapractice.repository.InformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

  private final BoardRepository boardRepository;
  private final InformationRepository informationRepository;

  @Autowired
  public BoardService(BoardRepository boardRepository,
      InformationRepository informationRepository) {
    this.boardRepository = boardRepository;
    this.informationRepository = informationRepository;
  }

  @Transactional
  public Long createPost(WritePostDto writePostDto) {
    Student student = informationRepository.getAccountById(
        writePostDto.getWriter()).getStudent();
    Account account = informationRepository.getAccountReference(
        writePostDto.getWriter());
    Post post = new Post().builder()
                          .subject(writePostDto.getPostSubject())
                          .postContent(writePostDto.getPostContent())
                          .account(account)
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
  public Long getPageCount() {
    Long postCount = boardRepository.countAllPost();
    Long pageCount = (postCount / 30) + 1;
    return pageCount;
  }

  @Transactional
  public PostDto getPost(Long postId) {
    Post post = boardRepository.getPost(postId);
    Account account = informationRepository.getAccountByStudentInfo(
        post.getStudent().getId());
    System.out.println(account.toString());

    List<Comment> comments = post.getComments();
    if (comments.isEmpty()) {
      PostDto postDto = new PostDto();
      postDto.setPostId(post.getId());
      postDto.setPostSubject(post.getPostSubject());
      postDto.setPostContent(post.getPostContent());
      postDto.setAccountId(account.getId());
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

  @Transactional
  public PostDto saveComment(String commentText, String accountId,
      Long postId) {
    Student student = informationRepository.getAccountById(accountId)
                                           .getStudent();
    Comment comment = new Comment()
        .builder()
        .commentText(commentText)
        .commentDate(LocalDateTime.now())
        .student(student)
        .post(informationRepository.getPostReference(postId))
        .build();

    boardRepository.saveComment(comment);
    return getPost(postId);
  }
}
