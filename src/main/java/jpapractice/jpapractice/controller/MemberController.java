package jpapractice.jpapractice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jpapractice.jpapractice.dto.StudentAndAccountDTO;
import jpapractice.jpapractice.service.MemberService;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/join")
    public String join() {
        return "member/joinPage";
    }

    @PostMapping("/join")
    public @ResponseBody StudentAndAccountDTO join(StudentAndAccountDTO studentAndAccountDTO) {
        StudentAndAccountDTO result = memberService.join(studentAndAccountDTO);

        return result;
    }

}
