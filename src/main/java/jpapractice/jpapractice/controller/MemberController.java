package jpapractice.jpapractice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jpapractice.jpapractice.dto.DefaultInfoDto;
import jpapractice.jpapractice.dto.StudentAndAccountDto;
import jpapractice.jpapractice.service.MemberService;

@Controller
@RequestMapping("/member") // 메서드들에 매핑된 URL 앞에 /mmeber path를 추가함
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/join")
    public String join(StudentAndAccountDto studentAndAccountDto) {
        return "member/joinPage";
    }

    @PostMapping("/join")
    public String join(@Valid StudentAndAccountDto studentAndAccountDto, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("studentAndAccountDTO", studentAndAccountDto);
            return "member/joinPage";
        }
        if (!studentAndAccountDto.getAccountPasswd().equals(studentAndAccountDto.getAccountPasswdCheck())) {
            bindingResult.rejectValue("accountPasswdCheck", "passwdIncorrect", "비밀번호가 일치하지 않습니다");
            return "member/joinPage";
        }
        String account = memberService.registMember(studentAndAccountDto);

        return "redirect:/login";
        // return "redirect:/mypage"; -> get 방식으로 파라미터가 넘어가게 된다
        // return "redirect:/member/mypage/{account}"; // -> path parameter 방법으로 데이터를 넘김

    }

    @GetMapping("/mypage/{account}") // path parameter을 받을 때의 방법
    public String mypage(@PathVariable("account") String account, Model model) {
        DefaultInfoDto result = memberService.findInfo(account);
        model.addAttribute("info", result);
        return "member/mypage";
    }
    // @PathVariable(parameter이름) 으로 값을 가져올수 있다.

}
