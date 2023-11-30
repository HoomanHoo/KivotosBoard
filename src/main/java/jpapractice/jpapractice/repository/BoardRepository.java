package jpapractice.jpapractice.repository;

import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jpapractice.jpapractice.domain.Post;
import jpapractice.jpapractice.domain.Student;
import jpapractice.jpapractice.dto.PostListDto;
import jpapractice.jpapractice.dto.WritePostDto;

@Repository
public class BoardRepository {
        private final EntityManager em;

        @Autowired
        public BoardRepository(EntityManager entityManager) {
                this.em = entityManager;

        }

        public Post savePost(Post post) {
                em.persist(post);
                return post;
        }

        public List<PostListDto> findPosts(int startPost) {
                int firstResult = startPost * 30;
                return em.createQuery(
                                "SELECT new jpapractice.jpapractice.dto.PostListDto(p.id, p.postSubject, p.postDate, s.name) "
                                                + "FROM Post p "
                                                + "JOIN p.student s "
                                                + "ORDER BY p.id DESC",
                                PostListDto.class)
                                .setFirstResult(firstResult)
                                .setMaxResults(30)
                                .getResultList();
                // post 엔티티의 student 필드를 통해 name을 가져오는 것이 게시글 수가 많아질 경우 계산이 비효율적일 것이라 예상
                // 따라서 어플리케이션에서 for문을 사용하지 않고 join을 통해서 원하는 컬럼만 가져올 수 있도록 하기 위해 Dto를 집어넣는 방식으로
                // 쿼리 사용
        }

        public Post getPost(Long postId) {
                return em.find(Post.class, postId);
        }

        // public List<Post> findPosts2(int startPost) {
        // int startPosition = startPost * 30;
        // return em.createQuery(
        // "SELECT p "
        // + "FROM Post p "
        // + "JOIN p.student s "
        // + "ORDER BY p.id DESC",
        // Post.class)
        // .setFirstResult(startPosition)
        // .setMaxResults(30)
        // .getResultList();
        // // post 엔티티의 student 필드를 통해 name을 가져오는 것이 게시글 수가 많아질 경우 계산이 비효율적일 것이라 예상
        // // 따라서 어플리케이션에서 for문을 사용하지 않고 join을 통해서 원하는 컬럼만 가져올 수 있도록 하기 위해 Dto를 집어넣는
        // 방식으로
        // // 쿼리 사용
        // }

        // public List<PostListDto> findPostsDto() {
        // return em.createQuery(
        // "SELECT new jpapractice.jpapractice.dto.PostListDto(p.id, p.postSubject,
        // p.postDate, s.name) "
        // + "FROM Post p "
        // + "JOIN p.student s "
        // + "ORDER BY p.id DESC",
        // PostListDto.class)
        // .getResultList();
        // // post 엔티티의 student 필드를 통해 name을 가져오는 것이 게시글 수가 많아질 경우 계산이 비효율적일 것이라 예상
        // // 따라서 어플리케이션에서 for문을 사용하지 않고 join을 통해서 원하는 컬럼만 가져올 수 있도록 하기 위해 Dto를 집어넣는
        // 방식으로
        // // 쿼리 사용
        // }

        // public List<Post> findPostsEntity() {
        // return em.createQuery(
        // "SELECT p "
        // + "FROM Post p "
        // + "JOIN p.student s "
        // + "ORDER BY p.id DESC",
        // Post.class)
        // .getResultList();
        // // post 엔티티의 student 필드를 통해 name을 가져오는 것이 게시글 수가 많아질 경우 계산이 비효율적일 것이라 예상
        // // 따라서 어플리케이션에서 for문을 사용하지 않고 join을 통해서 원하는 컬럼만 가져올 수 있도록 하기 위해 Dto를 집어넣는
        // 방식으로
        // // 쿼리 사용
        // }

        // public List<Post> findPostsFetch() {
        // return em.createQuery(
        // "SELECT p "
        // + "FROM Post p "
        // + "JOIN FETCH p.student s "
        // + "ORDER BY p.id DESC",
        // Post.class)
        // .getResultList();
        // // post 엔티티의 student 필드를 통해 name을 가져오는 것이 게시글 수가 많아질 경우 계산이 비효율적일 것이라 예상
        // // 따라서 어플리케이션에서 for문을 사용하지 않고 join을 통해서 원하는 컬럼만 가져올 수 있도록 하기 위해 Dto를 집어넣는
        // 방식으로
        // // 쿼리 사용
        // }

}