package simple.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import simple.board.SessionConst;
import simple.board.model.User;
import simple.board.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MemberController {
    private UserService userService;

    @Autowired
    public MemberController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("loginForm")
    String loginForm(@SessionAttribute(name=SessionConst.MY_SESSION_ID, required = false) User user, Model model){

        if(user != null){
            return "redirect:/";
        }

        model.addAttribute(new User());
        return "member/loginForm";
    }

    @PostMapping("login")
    String login(@ModelAttribute User user, BindingResult bindingResult, HttpServletRequest request) {
        User loginUser = userService.login(user);
        if(loginUser == null ) {
            bindingResult.addError(new ObjectError("user", "유저 아이디 또는 암호가 틀렸습니다"));
            return "member/loginForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.MY_SESSION_ID, user);

        return "redirect:/";
    }

    @GetMapping("logout")
    String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    @GetMapping("signupForm")
    String signupForm(Model model) {
        model.addAttribute(new User());
        return "member/signupForm";
    }

    @PostMapping("signup")
    String register(@ModelAttribute User user, BindingResult bindingResult){

        if(!StringUtils.hasText(user.getId())) {
            bindingResult.addError(new FieldError("user", "id", user.getId(), false, null, null, "유저명을 입력해주세요."));
        }

        if(userService.isDuplicateId(user.getId())) {
            bindingResult.addError(new FieldError("user", "id", user.getId(), false, null, null, "이미 등록된 유저입니다."));
        }

        if(bindingResult.hasErrors()) {
            return "member/signupForm";
        }

        userService.register(user);
        return "redirect:/";
    }

}
