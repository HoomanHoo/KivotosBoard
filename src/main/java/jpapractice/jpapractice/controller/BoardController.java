package jpapractice.jpapractice.controller;

import jakarta.validation.Valid;
import java.security.Principal;
import java.util.List;
import jpapractice.jpapractice.dto.PostDto;
import jpapractice.jpapractice.dto.PostListDto;
import jpapractice.jpapractice.dto.WritePostDto;
import jpapractice.jpapractice.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/post")
@AllArgsConstructor
public class BoardController {

  private final BoardService boardService;

  @GetMapping("/list/{pageNum}")
  public String list(@PathVariable int pageNum, Model model) {
    List<PostListDto> list = boardService.loadPosts(pageNum);
    model.addAttribute("list", list);
    model.addAttribute("pageNum", pageNum);
    model.addAttribute("pageCount", boardService.getPageCount());
    return "board/list";
  }

  @GetMapping("/create")
  public String create(WritePostDto post, Model model) {

    model.addAttribute("post", post);
    return "board/writePost";
  }

  @PostMapping("/create")
  public String create(@Valid WritePostDto writePostDto,
      BindingResult bindingResult, Principal principal,
      RedirectAttributes redirectAttributes) {

    if (bindingResult.hasErrors()) {
      return "board/writePost";
    }
    writePostDto.setWriter(principal.getName());
    Long postId = boardService.createPost(writePostDto);
    redirectAttributes.addAttribute("postId", postId);
    return "redirect:{postId}";
  }

  @GetMapping("/{postId}")
  public String post(@PathVariable("postId") Long postId, Model model) {
    PostDto postDto = boardService.getPost(postId);
    System.out.println(postDto.getAccountId());
    model.addAttribute("postDto", postDto);
    return "board/post";
  }

  @PostMapping("{postId}")
  public String editPost(@PathVariable("postId") Long postId,
      RedirectAttributes redirectAttributes, Principal principal) {
    redirectAttributes.addAttribute("postId", postId);
    return "redirect:post/{postId}";
  }

  @PostMapping("/{postId}/comment/create")
  public String commentCreate(@RequestParam String comment,
      @PathVariable("postId") Long postId, Principal principal,
      Model model) {

    PostDto postDto = boardService.saveComment(comment,
        principal.getName(), postId);
    model.addAttribute("postDto", postDto);

    return "redirect:/post/{postId}";
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("{postId}/modify")
  public String modifyPost(@PathVariable("postId") Long id,
      Principal principal, WritePostDto post, Model model) {
    model.addAttribute("createOrModify", "post/" + id + "/modify");
    PostDto postData = boardService.getPost(id);
    model.addAttribute("post", postData);
    return "board/writePost";
  }

}
