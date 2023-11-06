package jpapractice.jpapractice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jpapractice.jpapractice.dto.StudentAndAccountDto;
import jpapractice.jpapractice.service.MemberService;

@Controller
public class DefaultController {

    private final MemberService memberService;

    @Autowired
    public DefaultController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/login")
    public String login() {
        return "member/loginPage";
    }

    @PostMapping("/login")
    public String login(@RequestParam String id, @RequestParam String passwd) {
        return "redirect:/mypage";
    }

    @GetMapping("/join")
    public String join() {
        return "member/joinPage";
    }

    @PostMapping("/join")
    public String join(StudentAndAccountDto studentAndAccountDTO, RedirectAttributes redirectAttributes) {
        System.out.println(studentAndAccountDTO.toString());
        memberService.join(studentAndAccountDTO);
        redirectAttributes.addAttribute("account", studentAndAccountDTO.getAccountId());
        // return "redirect:/mypage"; -> get 방식으로 파라미터가 넘어가게 된다
        return "redirect:/member/mypage/{account}"; // -> path parameter 방법으로 데이터를 넘김
    }
}
