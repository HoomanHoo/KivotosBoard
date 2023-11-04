package jpapractice.jpapractice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jpapractice.jpapractice.dto.DefaultInfoDto;
import jpapractice.jpapractice.dto.StudentAndAccountDto;
import jpapractice.jpapractice.service.MemberService;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
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
    public String join(StudentAndAccountDto studentAndAccountDTO, RedirectAttributes re) {
        System.out.println(studentAndAccountDTO.toString());
        memberService.join(studentAndAccountDTO);
        re.addAttribute("account", studentAndAccountDTO.getAccountId());
        return "redirect:/mypage";
    }

    @GetMapping("/mypage")
    public String mypage(@RequestParam("account") String account, Model model) {
        DefaultInfoDto result = memberService.findInfo(account);
        model.addAttribute("info", result);
        return "member/mypage";
    }

}
