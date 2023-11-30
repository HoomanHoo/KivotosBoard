package jpapractice.jpapractice.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jpapractice.jpapractice.dto.PostDto;
import jpapractice.jpapractice.dto.PostListDto;
import jpapractice.jpapractice.dto.WritePostDto;
import jpapractice.jpapractice.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/boards")
@AllArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list/{pageNum}")
    public String list(@PathVariable int pageNum, Model model) {
        List<PostListDto> list = boardService.loadPosts(pageNum);
        model.addAttribute("list", list);
        return "board/list";
    }

    @GetMapping("/create")
    public String create(WritePostDto post) {

        return "board/writePost";
    }

    @PostMapping("/create")
    public String create(@Valid WritePostDto writePostDto, BindingResult bindingResult, Principal principal,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "board/writePost";
        }
        writePostDto.setWriter(principal.getName());
        Long postId = boardService.createPost(writePostDto);
        redirectAttributes.addAttribute("postId", postId);
        return "redirect:post/{postId}";
    }

    @GetMapping("/post/{postId}")
    public String post(@PathVariable("postId") Long postId, Model model) {
        PostDto postDto = boardService.getPost(postId);
        model.addAttribute("postDto", postDto);
        return "board/post";
    }

}
